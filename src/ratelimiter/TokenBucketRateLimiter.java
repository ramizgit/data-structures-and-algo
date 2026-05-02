package google.ratelimiter;

public class TokenBucketRateLimiter {

    private final int capacity;
    private final double refillRate;
    private double tokens;
    private long lastRefillTime;

    public TokenBucketRateLimiter(int capacity, double refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = capacity; // start full
        this.lastRefillTime = System.nanoTime();
    }

    public boolean allowRequest()
    {
        refill();

        if(this.tokens > 0){
            this.tokens--;
            return true; //allow request
        }

        return false; //reject request
    }

    private void refill()
    {
        // lazy/on-demand refill using elapsed time, NOT refill every fixed interval
        long now = System.nanoTime();
        double elapsedSeconds = (now - this.lastRefillTime) / 1e9;

        double tokensToAdd = elapsedSeconds * this.refillRate; //trigger based refill, rather than fixed time based
        this.tokens = Math.min(capacity, this.tokens + tokensToAdd);

        this.lastRefillTime = now;
    }

}
