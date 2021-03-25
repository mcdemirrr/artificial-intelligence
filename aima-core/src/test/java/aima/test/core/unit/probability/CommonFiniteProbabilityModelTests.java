package aima.test.core.unit.probability;

import org.junit.Assert;

import aima.core.probability.CategoricalDistribution;
import aima.core.probability.FiniteProbabilityModel;
import aima.core.probability.domain.FiniteIntegerDomain;
import aima.core.probability.example.ExampleRV;
import aima.core.probability.proposition.AssignmentProposition;
import aima.core.probability.proposition.ConjunctiveProposition;
import aima.core.probability.proposition.EquivalentProposition;
import aima.core.probability.proposition.IntegerSumProposition;

public abstract class CommonFiniteProbabilityModelTests extends
		CommonProbabilityModelTests {

	//
	// PROTECTED
	//
	protected void test_RollingPairFairDiceModel_Distributions(
			FiniteProbabilityModel model) {

		AssignmentProposition ad1_1 = new AssignmentProposition(
				ExampleRV.DICE_1_RV, 1);
		CategoricalDistribution dD1_1 = model.priorDistribution(ad1_1);
		Assert.assertArrayEquals(new double[] { 1.0 / 6.0 }, dD1_1.getValues(),
				DELTA_THRESHOLD);

		CategoricalDistribution dPriorDice1 = model
				.priorDistribution(ExampleRV.DICE_1_RV);
		Assert.assertArrayEquals(new double[] { 1.0 / 6.0, 1.0 / 6.0,
				1.0 / 6.0, 1.0 / 6.0, 1.0 / 6.0, 1.0 / 6.0 },
				dPriorDice1.getValues(), DELTA_THRESHOLD);

		CategoricalDistribution dPriorDice2 = model
				.priorDistribution(ExampleRV.DICE_2_RV);
		Assert.assertArrayEquals(new double[] { 1.0 / 6.0, 1.0 / 6.0,
				1.0 / 6.0, 1.0 / 6.0, 1.0 / 6.0, 1.0 / 6.0 },
				dPriorDice2.getValues(), DELTA_THRESHOLD);

		CategoricalDistribution dJointDice1Dice2 = model.jointDistribution(
				ExampleRV.DICE_1_RV, ExampleRV.DICE_2_RV);
		Assert.assertEquals(36, dJointDice1Dice2.getValues().length);
		for (int i = 0; i < dJointDice1Dice2.getValues().length; i++) {
			Assert.assertEquals(1.0 / 36.0, dJointDice1Dice2.getValues()[i],
					DELTA_THRESHOLD);
		}

		CategoricalDistribution dJointDice2Dice1 = model.jointDistribution(
				ExampleRV.DICE_2_RV, ExampleRV.DICE_1_RV);
		Assert.assertEquals(36, dJointDice2Dice1.getValues().length);
		for (int i = 0; i < dJointDice2Dice1.getValues().length; i++) {
			Assert.assertEquals(1.0 / 36.0, dJointDice2Dice1.getValues()[i],
					DELTA_THRESHOLD);
		}

		//
		// Test Sets of events
		IntegerSumProposition total11 = new IntegerSumProposition("Total",
				new FiniteIntegerDomain(11), ExampleRV.DICE_1_RV,
				ExampleRV.DICE_2_RV);
		// P<>(Total = 11) = <2.0/36.0>
		Assert.assertArrayEquals(new double[] { 2.0 / 36.0 }, model
				.priorDistribution(total11).getValues(), DELTA_THRESHOLD);

		// P<>(Dice1, Total = 11)
		// = <0.0, 0.0, 0.0, 0.0, 1.0/36.0, 1.0/36.0>
		Assert.assertArrayEquals(new double[] { 0, 0, 0, 0, 1.0 / 36.0,
				1.0 / 36.0 },
				model.priorDistribution(ExampleRV.DICE_1_RV, total11)
						.getValues(), DELTA_THRESHOLD);

		EquivalentProposition doubles = new EquivalentProposition("Doubles",
				ExampleRV.DICE_1_RV, ExampleRV.DICE_2_RV);
		// P(Doubles) = <1.0/6.0>
		Assert.assertArrayEquals(new double[] { 1.0 / 6.0 }, model
				.priorDistribution(doubles).getValues(), DELTA_THRESHOLD);

		//
		// Test posterior
		//
		// P<>(Dice1, Total = 11)
		// = <0.0, 0.0, 0.0, 0.0, 0.5, 0.5>
		Assert.assertArrayEquals(new double[] { 0, 0, 0, 0, 0.5, 0.5 }, model
				.posteriorDistribution(ExampleRV.DICE_1_RV, total11)
				.getValues(), DELTA_THRESHOLD);

		// P<>(Dice1 | Doubles) = <1/6, 1/6, 1/6, 1/6, 1/6, 1/6>
		Assert.assertArrayEquals(new double[] { 1.0 / 6.0, 1.0 / 6.0,
				1.0 / 6.0, 1.0 / 6.0, 1.0 / 6.0, 1.0 / 6.0 }, model
				.posteriorDistribution(ExampleRV.DICE_1_RV, doubles)
				.getValues(), DELTA_THRESHOLD);

		CategoricalDistribution dPosteriorDice1GivenDice2 = model
				.posteriorDistribution(ExampleRV.DICE_1_RV, ExampleRV.DICE_2_RV);
		Assert.assertEquals(36, dPosteriorDice1GivenDice2.getValues().length);
		for (int i = 0; i < dPosteriorDice1GivenDice2.getValues().length; i++) {
			Assert.assertEquals(1.0 / 6.0,
					dPosteriorDice1GivenDice2.getValues()[i], DELTA_THRESHOLD);
		}

		CategoricalDistribution dPosteriorDice2GivenDice1 = model
				.posteriorDistribution(ExampleRV.DICE_2_RV, ExampleRV.DICE_1_RV);
		Assert.assertEquals(36, dPosteriorDice2GivenDice1.getValues().length);
		for (int i = 0; i < dPosteriorDice2GivenDice1.getValues().length; i++) {
			Assert.assertEquals(1.0 / 6.0,
					dPosteriorDice2GivenDice1.getValues()[i], DELTA_THRESHOLD);
		}
	}

	protected void test_infectedcoughfeverModel_Distributions(
			FiniteProbabilityModel model) {

		AssignmentProposition ainfected = new AssignmentProposition(
				ExampleRV.INFECTED_RV, Boolean.TRUE);
		AssignmentProposition anotinfected = new AssignmentProposition(
				ExampleRV.INFECTED_RV, Boolean.FALSE);
		AssignmentProposition afever = new AssignmentProposition(
				ExampleRV.FEVER_RV, Boolean.TRUE);
		AssignmentProposition anotfever = new AssignmentProposition(
				ExampleRV.FEVER_RV, Boolean.FALSE);

		// AIMA3e pg. 493
		// P<>(cough | infected) = <0.6, 0.4>
		Assert.assertArrayEquals(new double[] { 0.6, 0.4 }, model
				.posteriorDistribution(ExampleRV.COUGH_RV, ainfected)
				.getValues(), DELTA_THRESHOLD);

		// AIMA3e pg. 497
		// P<>(cough | infected AND fever) = <0.871, 0.129>
		Assert.assertArrayEquals(
				new double[] { 0.8709677419354839, 0.12903225806451615 },
				model.posteriorDistribution(ExampleRV.COUGH_RV, ainfected,
						afever).getValues(), DELTA_THRESHOLD);

		// AIMA3e pg. 498
		// (13.17)
		// P<>(infected AND fever | cough)
		// = P<>(infected | cough)P<>(fever | cough)
		ConjunctiveProposition infectedAndfever = new ConjunctiveProposition(
				ainfected, afever);
		Assert.assertArrayEquals(
				model.posteriorDistribution(infectedAndfever,
						ExampleRV.COUGH_RV).getValues(),
				model.posteriorDistribution(ainfected, ExampleRV.COUGH_RV)
						.multiplyBy(
								model.posteriorDistribution(afever,
										ExampleRV.COUGH_RV)).getValues(),
				DELTA_THRESHOLD);

		// (13.18)
		// P<>(cough | infected AND fever)
		// = &alpha;P<>(infected | cough)P<>(fever | cough)P(cough)
		Assert.assertArrayEquals(
				model.posteriorDistribution(ExampleRV.COUGH_RV,
						infectedAndfever).getValues(),
				model.posteriorDistribution(ainfected, ExampleRV.COUGH_RV)
						.multiplyBy(
								model.posteriorDistribution(afever,
										ExampleRV.COUGH_RV))
						.multiplyBy(
								model.priorDistribution(ExampleRV.COUGH_RV))
						.normalize().getValues(), DELTA_THRESHOLD);

		// (13.19)
		// P<>(infected, fever | cough)
		// = P<>(infected | cough)P<>(fever | cough)
		ConjunctiveProposition infectedAndfeverRV = new ConjunctiveProposition(
				ExampleRV.INFECTED_RV, ExampleRV.FEVER_RV);
		Assert.assertArrayEquals(
				model.posteriorDistribution(infectedAndfeverRV,
						ExampleRV.COUGH_RV).getValues(),
				model.posteriorDistribution(ExampleRV.INFECTED_RV,
						ExampleRV.COUGH_RV)
						.multiplyByPOS(
								model.posteriorDistribution(ExampleRV.FEVER_RV,
										ExampleRV.COUGH_RV),
								ExampleRV.INFECTED_RV, ExampleRV.FEVER_RV,
								ExampleRV.COUGH_RV).getValues(),
				DELTA_THRESHOLD);

		// (product rule)
		// P<>(infected, fever, cough)
		// = P<>(infected, fever | cough)P<>(cough)
		Assert.assertArrayEquals(
				model.priorDistribution(ExampleRV.INFECTED_RV,
						ExampleRV.FEVER_RV, ExampleRV.COUGH_RV).getValues(),
				model.posteriorDistribution(infectedAndfeverRV,
						ExampleRV.COUGH_RV)
						.multiplyBy(
								model.priorDistribution(ExampleRV.COUGH_RV))
						.getValues(), DELTA_THRESHOLD);

		// (using 13.19)
		// P<>(infected, fever | cough)P<>(cough)
		// = P<>(infected | cough)P<>(fever | cough)P<>(cough)
		Assert.assertArrayEquals(
				model.posteriorDistribution(infectedAndfeverRV,
						ExampleRV.COUGH_RV)
						.multiplyBy(
								model.priorDistribution(ExampleRV.COUGH_RV))
						.getValues(),
				model.posteriorDistribution(ExampleRV.INFECTED_RV,
						ExampleRV.COUGH_RV)
						.multiplyByPOS(
								model.posteriorDistribution(ExampleRV.FEVER_RV,
										ExampleRV.COUGH_RV)
										.multiplyBy(
												model.priorDistribution(ExampleRV.COUGH_RV)),
								ExampleRV.INFECTED_RV, ExampleRV.FEVER_RV,
								ExampleRV.COUGH_RV).getValues(),
				DELTA_THRESHOLD);
		//
		// P<>(infected, fever, cough)
		// = P<>(infected | cough)P<>(fever | cough)P<>(cough)
		Assert.assertArrayEquals(
				model.priorDistribution(ExampleRV.INFECTED_RV,
						ExampleRV.FEVER_RV, ExampleRV.COUGH_RV).getValues(),
				model.posteriorDistribution(ExampleRV.INFECTED_RV,
						ExampleRV.COUGH_RV)
						.multiplyByPOS(
								model.posteriorDistribution(ExampleRV.FEVER_RV,
										ExampleRV.COUGH_RV),
								ExampleRV.INFECTED_RV, ExampleRV.FEVER_RV,
								ExampleRV.COUGH_RV)
						.multiplyBy(
								model.priorDistribution(ExampleRV.COUGH_RV))
						.getValues(), DELTA_THRESHOLD);

		// AIMA3e pg. 496
		// General case of Bayes' Rule
		// P<>(Y | X) = P<>(X | Y)P<>(Y)/P<>(X)
		// Note: Performing in this order -
		// P<>(Y | X) = (P<>(Y)P<>(X | Y))/P<>(X)
		// as default multiplication of distributions are not commutative (could
		// also use pointwiseProductPOS() to specify the order).
		Assert.assertArrayEquals(
				model.posteriorDistribution(ExampleRV.COUGH_RV,
						ExampleRV.INFECTED_RV).getValues(),
				model.priorDistribution(ExampleRV.COUGH_RV)
						.multiplyBy(
								model.posteriorDistribution(
										ExampleRV.INFECTED_RV,
										ExampleRV.COUGH_RV))
						.divideBy(
								model.priorDistribution(ExampleRV.INFECTED_RV))
						.getValues(), DELTA_THRESHOLD);

		Assert.assertArrayEquals(
				model.posteriorDistribution(ExampleRV.COUGH_RV,
						ExampleRV.FEVER_RV).getValues(),
				model.priorDistribution(ExampleRV.COUGH_RV)
						.multiplyBy(
								model.posteriorDistribution(ExampleRV.FEVER_RV,
										ExampleRV.COUGH_RV))
						.divideBy(model.priorDistribution(ExampleRV.FEVER_RV))
						.getValues(), DELTA_THRESHOLD);

		// General Bayes' Rule conditionalized on background evidence e (13.3)
		// P<>(Y | X, e) = P<>(X | Y, e)P<>(Y|e)/P<>(X | e)
		// Note: Performing in this order -
		// P<>(Y | X, e) = (P<>(Y|e)P<>(X | Y, e)))/P<>(X | e)
		// as default multiplication of distributions are not commutative (could
		// also use pointwiseProductPOS() to specify the order).
		Assert.assertArrayEquals(
				model.posteriorDistribution(ExampleRV.COUGH_RV,
						ExampleRV.INFECTED_RV, afever).getValues(),
				model.posteriorDistribution(ExampleRV.COUGH_RV, afever)
						.multiplyBy(
								model.posteriorDistribution(
										ExampleRV.INFECTED_RV,
										ExampleRV.COUGH_RV, afever))
						.divideBy(
								model.posteriorDistribution(
										ExampleRV.INFECTED_RV, afever))
						.getValues(), DELTA_THRESHOLD);
		//
		Assert.assertArrayEquals(
				model.posteriorDistribution(ExampleRV.COUGH_RV,
						ExampleRV.INFECTED_RV, anotfever).getValues(),
				model.posteriorDistribution(ExampleRV.COUGH_RV, anotfever)
						.multiplyBy(
								model.posteriorDistribution(
										ExampleRV.INFECTED_RV,
										ExampleRV.COUGH_RV, anotfever))
						.divideBy(
								model.posteriorDistribution(
										ExampleRV.INFECTED_RV, anotfever))
						.getValues(), DELTA_THRESHOLD);
		//
		Assert.assertArrayEquals(
				model.posteriorDistribution(ExampleRV.COUGH_RV,
						ExampleRV.FEVER_RV, ainfected).getValues(),
				model.posteriorDistribution(ExampleRV.COUGH_RV, ainfected)
						.multiplyBy(
								model.posteriorDistribution(ExampleRV.FEVER_RV,
										ExampleRV.COUGH_RV, ainfected))
						.divideBy(
								model.posteriorDistribution(ExampleRV.FEVER_RV,
										ainfected)).getValues(),
				DELTA_THRESHOLD);

		Assert.assertArrayEquals(
				model.posteriorDistribution(ExampleRV.COUGH_RV,
						ExampleRV.FEVER_RV, anotinfected).getValues(),
				model.posteriorDistribution(ExampleRV.COUGH_RV, anotinfected)
						.multiplyBy(
								model.posteriorDistribution(ExampleRV.FEVER_RV,
										ExampleRV.COUGH_RV, anotinfected))
						.divideBy(
								model.posteriorDistribution(ExampleRV.FEVER_RV,
										anotinfected)).getValues(),
				DELTA_THRESHOLD);
	}

	protected void test_InfectedCoughFeverVitamin_D_levelModel_Distributions(
			FiniteProbabilityModel model) {

		AssignmentProposition asunny = new AssignmentProposition(
				ExampleRV.VÝTAMÝN_D_LEVEL_RV, "sunny");
		AssignmentProposition acough = new AssignmentProposition(
				ExampleRV.COUGH_RV, Boolean.TRUE);

		// Should be able to run all the same queries for this independent
		// sub model.
		test_InfectedCoughFeverVitamin_D_levelModel_Distributions(model);

		// AIMA3e pg. 487
		// P(sunny, cough)
		// Would be a two-element vector giving the probabilities of a sunny day
		// with a cough and a sunny day with no cough.
		Assert.assertArrayEquals(new double[] { 0.12, 0.48 }, model
				.priorDistribution(asunny, ExampleRV.COUGH_RV).getValues(),
				DELTA_THRESHOLD);

		// AIMA3e pg. 488 (i.e. one element Vector returned)
		// P(sunny, cough)
		Assert.assertArrayEquals(new double[] { 0.12 }, model
				.priorDistribution(asunny, acough).getValues(),
				DELTA_THRESHOLD);
		// P(sunny AND cough)
		Assert.assertArrayEquals(new double[] { 0.12 }, model
				.priorDistribution(new ConjunctiveProposition(asunny, acough))
				.getValues(), DELTA_THRESHOLD);
		// P(sunny) = <0.6>
		Assert.assertArrayEquals(new double[] { 0.6 },
				model.priorDistribution(asunny).getValues(), DELTA_THRESHOLD);
	}

	// AIMA3e pg. 496
	protected void test_MeningitisStiffNeckModel_Distributions(
			FiniteProbabilityModel model) {

		AssignmentProposition astiffNeck = new AssignmentProposition(
				ExampleRV.STIFF_NECK_RV, true);

		// AIMA3e pg. 497
		// P<>(Mengingitis | stiffneck) = &alpha;<P(s | m)P(m), P(s | ~m)P(~m)>
		CategoricalDistribution dMeningitisGivenStiffNeck = model
				.posteriorDistribution(ExampleRV.MENINGITIS_RV, astiffNeck);
		Assert.assertEquals(2, dMeningitisGivenStiffNeck.getValues().length);
		Assert.assertEquals(0.0014, dMeningitisGivenStiffNeck.getValues()[0],
				DELTA_THRESHOLD);
		Assert.assertEquals(0.9986, dMeningitisGivenStiffNeck.getValues()[1],
				DELTA_THRESHOLD);
	}

	protected void test_BurglaryAlarmModel_Distributions(
			FiniteProbabilityModel model) {

		AssignmentProposition aburglary = new AssignmentProposition(
				ExampleRV.BURGLARY_RV, Boolean.TRUE);
		AssignmentProposition anotburglary = new AssignmentProposition(
				ExampleRV.BURGLARY_RV, Boolean.FALSE);
		AssignmentProposition anotearthquake = new AssignmentProposition(
				ExampleRV.EARTHQUAKE_RV, Boolean.FALSE);
		AssignmentProposition ajohnCalls = new AssignmentProposition(
				ExampleRV.JOHN_CALLS_RV, Boolean.TRUE);
		AssignmentProposition amaryCalls = new AssignmentProposition(
				ExampleRV.MARY_CALLS_RV, Boolean.TRUE);

		// AIMA3e. pg. 514
		// P<>(Alarm | JohnCalls = true, MaryCalls = true, Burglary = false,
		// Earthquake = false)
		// = <0.558, 0.442>
		Assert.assertArrayEquals(
				new double[] { 0.5577689243027888, 0.44223107569721115 },
				model.posteriorDistribution(ExampleRV.ALARM_RV, ajohnCalls,
						amaryCalls, anotburglary, anotearthquake).getValues(),
				DELTA_THRESHOLD);

		// AIMA3e pg. 523
		// P<>(Burglary | JohnCalls = true, MaryCalls = true) = <0.284, 0.716>
		Assert.assertArrayEquals(
				new double[] { 0.2841718353643929, 0.7158281646356071 },
				model.posteriorDistribution(ExampleRV.BURGLARY_RV, ajohnCalls,
						amaryCalls).getValues(), DELTA_THRESHOLD);

		// AIMA3e pg. 528
		// P<>(JohnCalls | Burglary = true)
		Assert.assertArrayEquals(new double[] { 0.8490169999999999,
				0.15098299999999998 },
				model.posteriorDistribution(ExampleRV.JOHN_CALLS_RV, aburglary)
						.getValues(), DELTA_THRESHOLD);
	}
}
