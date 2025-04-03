package com.betting.feed.adapter.feed.v1.service;

import com.betting.feed.adapter.exception.CacheException;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.async.RedisAsyncCommands;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class RedisService {

    @Autowired
    RedisAsyncCommands<String, String> commands;


    public boolean saveWithExpiration(String key, Object value, int lifeTime) {
        Object result = null;
        if (StringUtils.isEmpty(key)) {
            throw new CacheException(" Unable to delete the KEY. Empty key");
        }
        if (value == null || "".equals(value)) {
            throw new CacheException(" Unable to delete the VALUE. Empty value");
        }
        RedisFuture<String> future = null;
        try {
            RedisFuture<Boolean> futureB = commands.setnx(key, (String)value);
            if (!futureB.get(5, TimeUnit.SECONDS)) {
                return false;
            }
            if (lifeTime > 0) {
                future = commands.setex(key, lifeTime, (String)value);
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        try {
            result = (String) future.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            throw new CacheException(" Redis getData... unable to SET key value: " + key + ", Error: " + ex.getMessage());
        }
        return "OK".equals(result);
    }

    public boolean save(String key, Object value) {
        return this.saveWithExpiration(key, value, 0);
    }

    public String getData(String key) {
        String value = null;
        if (StringUtils.isEmpty(key)) {
            throw new CacheException(" Unable to get the KEY. Empty key");
        } // end if
        RedisFuture<String> future = null;
        try {
            future = commands.get(key);
        } catch (Exception e) {
            return null;
        } // end try - catch
        try {
            value = future.get();
        } catch (InterruptedException | ExecutionException ex) {
            throw new CacheException(" Redis getData... unable to GET key value: " + key + ", Error: " + ex.getMessage());
        } // end try - catch
        return value;
    } // end method getData(String, Logger)


    public boolean delete(String key) {
        RedisFuture<Long> future = null;
        long result = 0;
        try {
            future = commands.del(key);
        } catch (Exception e) {
            throw new CacheException(" Redis deleteData exception... unable to DEL key value: " + key + ", Error: " + e.getMessage());
        } // end try - catch
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException ex) {
            throw new CacheException(" Redis deleteData... unable to DEL key value: " + key + ", Error: " + ex.getMessage());
        } // end try - catch
        return 1 == result;
    }

    public boolean exists(String key) {
        RedisFuture<Long> response = commands.exists(key);
        long result = 0;
        try {
            result = response.get();
        } catch (InterruptedException | ExecutionException ex) {
            throw new CacheException(" Redis exists... unable to check key value: " + key + ", Error: " + ex.getMessage());
        } // end try - catch
        return 1 == result;
    }

}

