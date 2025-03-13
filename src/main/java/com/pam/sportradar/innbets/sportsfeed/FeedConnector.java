package com.pam.sportradar.innbets.sportsfeed;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pam.sportradar.innbets.kafka.Producer;
import com.pam.sportradar.innbets.sportsfeed.listener.CustomUofGlobalEventsListener;
import com.pam.sportradar.innbets.sportsfeed.listener.CustomUofListener;
import com.sportradar.unifiedodds.sdk.MessageInterest;
import com.sportradar.unifiedodds.sdk.UofSdk;
import com.sportradar.unifiedodds.sdk.cfg.UofConfiguration;
import com.sportradar.unifiedodds.sdk.managers.SportDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Configuration
public class FeedConnector {
    private final Producer producer;
    private final ObjectMapper objectMapper;
    @Value("${uof.access-token}")
    private String accessToken;
    @Value("${uof.api-host}")
    private String apiHost;
    @Value("${uof.api-port}")
    private int apiPort;
    @Value("${uof.use-ssl}")
    private boolean useSSL;
    @Value("${uof.messaging.host}")
    private String messagingHost;
    @Value("${uof.messaging.username}")
    private String messagingUsername;
    @Value("${uof.messaging.password}")
    private String messagingPassword;
    @Value("${uof.messaging.port}")
    private int messagingPort;
    @Value("${uof.messaging.use-ssl}")
    private boolean messagingUseSSL;
    @Value("${uof.messaging.virtual-host}")
    private String messagingVirtualHost;
    @Value("${uof.message-interest}")
    private String messageInterest;
    @Value("${feed.lang}")
    private String feedLang;

    public FeedConnector(Producer producer, ObjectMapper objectMapper) {
        this.producer = producer;
        this.objectMapper = objectMapper;
    }

    private Thread createSessionThread(UofSdk sdk, MessageInterest interest, CustomUofListener listener, String threadName, CountDownLatch latch) {
        Thread thread = new Thread(() -> {
            try {
                sdk.getSessionBuilder()
                        .setMessageInterest(interest)
                        .setListener(listener)
                        .build();
                log.info("{} session initialized successfully.", threadName);
                latch.countDown();
                sdk.open();
                synchronized (Thread.currentThread()) {
                    Thread.currentThread().wait();
                }
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                log.error("{} thread interrupted.", threadName, ie);
            } catch (Exception e) {
                log.error("Error initializing {} session: {}", threadName, e.getMessage(), e);
            }
        }, threadName);
        thread.setDaemon(false);
        return thread;
    }

    @Bean
    public Map<String, UofSdk> connect() {
        try {
            Map<String, UofSdk> sdkList = new HashMap<>();
            CustomUofListener prematchListener = new CustomUofListener(producer);
            CustomUofListener liveListener = new CustomUofListener(producer);
            CustomUofGlobalEventsListener globalEventsListener = new CustomUofGlobalEventsListener();
            UofConfiguration config = UofSdk.getUofConfigurationBuilder()
                    .setAccessToken(accessToken)
                    .selectCustom()
                    .setApiHost(apiHost)
                    .setApiPort(apiPort)
                    .setApiUseSsl(useSSL)
                    .setMessagingHost(messagingHost)
                    .setMessagingUsername(messagingUsername)
                    .setMessagingPassword(messagingPassword)
                    .setMessagingPort(messagingPort)
                    .setMessagingUseSsl(messagingUseSSL)
                    .setMessagingVirtualHost(messagingVirtualHost)
                    .setDefaultLanguage(Locale.forLanguageTag(feedLang))
                    .build();
            UofSdk prematchSdk= new UofSdk(globalEventsListener, config);
            UofSdk liveSdk = new UofSdk(globalEventsListener, config);
            CountDownLatch sessionLatch = new CountDownLatch(2);
            Thread preMatchThread = createSessionThread(prematchSdk, MessageInterest.PrematchMessagesOnly, prematchListener,
                    "PreMatch-Thread", sessionLatch);
            Thread liveThread = createSessionThread(liveSdk, MessageInterest.LiveMessagesOnly, liveListener,
                    "Live-Thread", sessionLatch);
            preMatchThread.start();
            liveThread.start();
            log.info("🏆 Prematch & Live THREADS started successfully! 🏆");
            sdkList.put("prematch-sdk", prematchSdk);
            sdkList.put("live-sdk", liveSdk);
            sessionLatch.await();
            return sdkList;
        } catch (Exception e) {
            log.error("Error initializing UOF SDK: {}", e.getMessage(), e);
            throw new RuntimeException("Error initializing UOF SDK", e);
        }
    }

    @Bean
    public UofSdk getPrematchSdk(Map<String, UofSdk> sdkMap) {
        return sdkMap.get("prematch-sdk");
    }

}
