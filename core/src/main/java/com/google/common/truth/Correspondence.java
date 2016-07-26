/*
 * Copyright (c) 2016 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.common.truth;

import javax.annotation.Nullable;

/**
 * Determines whether an instance of type {@code A} corresponds in some way to an instance of type
 * {@code E}. For example, an implementation might implement approximate equality between numeric
 * values, with values being said to correspond if the difference between them is does not exceed
 * some fixed tolerance. (TODO(b/29966314): Replace "an implementation" with a reference to the
 * tolerance method once it exists.) The instances of type {@code A} are typically actual values
 * from a collection returned by the code under test; the instances of type {@code E} are typically
 * expected values with which the actual values are compared by the test.
 *
 * <p>The correspondence is required to be consistent: for any given values {@code actual} and
 * {@code expected}, multiple invocations of {@code compare(actual, expected)} must consistently
 * return {@code true} or consistently return {@code false} (provided that neither value is
 * modified). Although {@code A} and {@code E} will often be the same types, they are <i>not</i>
 * required to be the same, and even if they are it is <i>not</i> required that the correspondence
 * should have any of the other properties of an equivalence relation (reflexivity, symmetry, or
 * transitivity).
 *
 * <p>Instances of this are typically used via {@link IterableSubject#comparingElementsUsing}.
 * (TODO(b/29966314): Mention MapSubject and MultimapSubject methods when they're added.)
 *
 * @author Pete Gillin
 */
public abstract class Correspondence<A, E> {

  /**
   * Returns whether or not the {@code actual} value is said to correspond to the {@code expected}
   * value for the purposes of this test.
   */
  public abstract boolean compare(@Nullable A actual, @Nullable E expected);

  /**
   * Returns a description of the correspondence, suitable to fill the gap in a failure message of
   * the form {@code "Not true that <[a1, a2, a3]> contains exactly elements which ... <expected>"}.
   *
   * <p>For example, for a {@code Correspondence<String, Integer>} which tests whether the actual
   * string parses to the expected integer, this would return {@code "parse to"} to result in a
   * failure message of the form {@code "Not true that <[foo, 123, bar]> contains exactly elements
   * which parse to <[123, 456]>"}.
   */
  @Override
  public abstract String toString();

  /**
   * @throws UnsupportedOperationException always
   * @deprecated {@link Object#equals(Object)} is not supported. If you meant to compare objects
   *     using this {@link Correspondence}, use {@link #compare}.
   */
  @Deprecated
  @Override
  public final boolean equals(@Nullable Object o) {
    throw new UnsupportedOperationException(
        "Correspondence.equals(object) is not supported. If you meant to compare objects, use"
            + " .compare(actual, expected) instead.");
  }

  /**
   * @throws UnsupportedOperationException always
   * @deprecated {@link Object#hashCode()} is not supported.
   */
  @Deprecated
  @Override
  public final int hashCode() {
    throw new UnsupportedOperationException("Correspondence.hashCode() is not supported.");
  }
}
