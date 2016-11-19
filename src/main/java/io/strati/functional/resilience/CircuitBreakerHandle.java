package io.strati.functional.resilience;

/**
 * Protected code can accept a CircuitBreakerHandle to communicate with the CircuitBreaker by
 * method calls instead of throwing exceptions.
 */
public interface CircuitBreakerHandle {

  /**
   * Notifies the current CircuitBreaker of a failure. Nevertheless the protected code can
   * finish execution and a possible result will be returned.
   *
   * <p>Example:</p>
   *
   * <code><pre>
   * CircuitBreaker cb = CircuitBreakerBuilder.create()
   *                                          .threshold(1)
   *                                          .build();
   * Try<Integer> result = cb.attempt(circuitBreakerHandle -> {
   *   if (somethingFailed()) {
   *     circuitBreakerHandle.notifyFailure();
   *   }
   *   return 42;
   * });
   * assertTrue(result.isSuccess());
   * assertEquals(42, result.get().intValue());
   * assertTrue(cb.isOpen());
   * </pre></code>
   */
  void notifyFailure();

}
