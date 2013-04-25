package com.sw_engineering_candies;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.meta.When;

public class FindbugsWarningsByExample4 {

	public static void main(final String[] args) {

		System.out.println("Findbugs Sample for NP_NONNULL_RETURN_VIOLATION ");
		// Confidence: High, Rank: Scary (6)
		// Pattern: NP_NONNULL_RETURN_VIOLATION
		// Type: NP, Category: CORRECTNESS (Correctness)
		//
		// WRONG code, but CORRECT warning in method
		String tempA = FindbugsWarningsByExample4
				.getSometimesNullValue_Nonnull_Annotation_WRONG();
		try {
			System.out.println("   - " + tempA.length());
		} catch (final NullPointerException e) {
			System.out.println("   - " + e.getMessage());
		}
		// CORRECT code
		String tempB = FindbugsWarningsByExample4
				.getNeverNullValue_Nonnull_Annotation_CORRECT();
		System.out.println("   - " + tempB.length());

		System.out
				.println("\nFindbugs Sample for NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE");
		// Confidence: Normal, Rank: Troubling (13)
		// Pattern: NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE
		// Type: NP, Category: STYLE (Dodgy code)
		//
		// WRONG code but no warning
		String tempC = FindbugsWarningsByExample4
				.getSometimesNullValue_WithoutAnnotation_WRONG();
		try {
			System.out.println("   - " + tempC.length());
		} catch (final NullPointerException e) {
			System.out.println("   - " + e.getMessage());
		}
		// WRONG code, but CORRECT warning form Findbugs
		String tempD = FindbugsWarningsByExample4
				.getSometimesNullValue_Notnull_MaybeNull_Annotation_CORRECT();
		try {
			// Next line should show NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE
			System.out.println("   - " + tempD.length());
		} catch (final Exception e) {
			System.out.println("   - " + e.getMessage());
		}

		System.out.println("\nFindbugs Sample for RV_RETURN_VALUE_IGNORED ");
		// Confidence: Low, Rank: Scary (8)
		// Pattern: RV_RETURN_VALUE_IGNORED
		// Type: RV, Category: CORRECTNESS (Correctness)
		//
		// NO RETURN CHECK - without Findbugs warning
		FindbugsWarningsByExample4
				.getSometimesNullValue_NonWithCheckReturnValueAnnotation_WRONG();
		//
		// NO RETURN CHECK - with Findbugs warning
		// Next line should show RV_RETURN_VALUE_IGNORED
		FindbugsWarningsByExample4.getWithCheckReturnValue_Annotation_CORRECT();
		//
		// RETURN CHECK - CORRECT code
		final String tempE = FindbugsWarningsByExample4
				.getWithCheckReturnValue_Annotation_CORRECT();
		System.out.println("   - " + tempE);
	}

	public static @Nonnull
	String getNeverNullValue_Nonnull_Annotation_CORRECT() {
		return Math.random() < 0.5 ? "first value" : "second value";
	}

	public static @Nonnull
	String getSometimesNullValue_Nonnull_Annotation_WRONG() {
		// Next line should show NP_NONNULL_RETURN_VIOLATION
		return Math.random() < 0.5 ? null : "second value";
	}

	public static @Nonnull(when = When.MAYBE)
	String getSometimesNullValue_Notnull_MaybeNull_Annotation_CORRECT() {
		return Math.random() < 0.5 ? null : "second value";
	}

	public static String getSometimesNullValue_WithoutAnnotation_WRONG() {
		return Math.random() < 0.5 ? null : "second value";
	}

	public static String getSometimesNullValue_NonWithCheckReturnValueAnnotation_WRONG() {
		return Math.random() < 0.5 ? null : "second value";
	}

	@CheckReturnValue
	public static String getWithCheckReturnValue_Annotation_CORRECT() {
		return Math.random() < 0.5 ? null : "second value";
	}

}