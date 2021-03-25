package aima.core.probability.example;

import aima.core.probability.full.FullJointDistributionModel;

/**
 * 
 * @author Ciaran O'Reilly
 *
 */
public class FullJointDistribution›nfectedCoughFeverVitamin_D_levelModel extends
		FullJointDistributionModel {

	public FullJointDistribution›nfectedCoughFeverVitamin_D_levelModel() {
		super(new double[] {
				// infected = true, cough = true, fever = true, vitamin_D-level =
				// high
				0.0648,
				// infected = true, cough = true, fever = true, vitamin_D-level = normal
				0.0108,
				// infected = true, cough = true, fever = true, vitamin_D-level =
				// low
				0.03132,
				// infected = true, cough = true, fever = true, vitamin_D-level = low
				0.00108,
				// infected = true, cough = true, fever = false, vitamin_D-level =
				// high
				0.0072,
				// infected = true, cough = true, fever = false, vitamin_D-level =
				// normal
				0.0012,
				// infected = true, cough = true, fever = false, vitamin_D-level =
				// low
				0.00348,
				// infected = true, cough = true, fever = false, vitamin_D-level =
				// low
				0.00012,
				// infected = true, cough = false, fever = true, vitamin_D-level =
				// high
				0.0096,
				// infected = true, cough = false, fever = true, vitamin_D-level =
				// normal
				0.0016,
				// infected = true, cough = false, fever = true, vitamin_D-level =
				// low
				0.00464,
				// infected = true, cough = false, fever = true, vitamin_D-level =
				// low
				0.00016,
				// infected = true, cough = false, fever = false, vitamin_D-level =
				// high
				0.0384,
				// infected = true, cough = false, fever = false, vitamin_D-level =
				// normal
				0.0064,
				// infected = true, cough = false, fever = false, vitamin_D-level =
				// low
				0.01856,
				// infected = true, cough = false, fever = false, vitamin_D-level =
				// low
				0.00064,
				// infected = false, cough = true, fever = true, vitamin_D-level =
				// high
				0.0432,
				// infected = false, cough = true, fever = true, vitamin_D-level =
				// normal
				0.0072,
				// infected = false, cough = true, fever = true, vitamin_D-level =
				// low
				0.02088,
				// infected = false, cough = true, fever = true, vitamin_D-level =
				// low
				0.00072,
				// infected = false, cough = true, fever = false, vitamin_D-level =
				// high
				0.0048,
				// infected = false, cough = true, fever = false, vitamin_D-level =
				// normal
				0.0008,
				// infected = false, cough = true, fever = false, vitamin_D-level =
				// low
				0.00232,
				// infected = false, cough = true, fever = false, vitamin_D-level =
				// low
				0.00008,
				// infected = false, cough = false, fever = true, vitamin_D-level =
				// high
				0.0864,
				// infected = false, cough = false, fever = true, vitamin_D-level =
				// normal
				0.0144,
				// infected = false, cough = false, fever = true, vitamin_D-level =
				// low
				0.04176,
				// infected = false, cough = false, fever = true, vitamin_D-level =
				// low
				0.00144,
				// infected = false, cough = false, fever = false, vitamin_D-level =
				// high
				0.3456,
				// infected = false, cough = false, fever = false, vitamin_D-level =
				// normal
				0.0576,
				// infected = false, cough = false, fever = false, vitamin_D-level =
				// low
				0.16704,
				// infected = false, cough = false, fever = false, vitamin_D-level =
				// low
				0.00576 }, ExampleRV.INFECTED_RV, ExampleRV.COUGH_RV,
				ExampleRV.FEVER_RV, ExampleRV.V›TAM›N_D_LEVEL_RV);
	}
}
