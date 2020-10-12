package ai;

import java.util.ArrayList;

import environment.Car;
//-78375d
public class NeuralNetwork {
	private Double[] bias = {
							0.0d,0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0d, 0d,
							0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0d, 0d,
							0d,0d, 0d, 0d
							};
	private Car car;
	private int w = 0;
	
	public NeuralNetwork() {
		
	}
	
	public ArrayList<Double> getSinapse(ArrayList<Double> input, Car car){
		this.car = car;
		ArrayList<Double> firstHiddenLayer = new ArrayList<Double>();
		ArrayList<Double> secondHiddenLayer = new ArrayList<Double>();
		ArrayList<Double> outputNeuralNet = new ArrayList<Double>();
		

		
		firstHiddenLayer = this.getNeuronLayer(input, 8, 0, 10000000); 
		//secondHiddenLayer = this.getNeuronLayer(firstHiddenLayer, 16, 8, 10000);
		outputNeuralNet = this.getNeuronLayer(firstHiddenLayer, 12, 8, 1);
		
		this.w = 0;
		for(int i = 0; i < firstHiddenLayer.size(); i++) {
			System.out.println("first OutPut: " + i + " " +  firstHiddenLayer.get(i));
			
		}
		System.out.println("---------------------------");
//		for(int i = 0; i < firstHiddenLayer.size(); i++) {
//			System.out.println("second OutPut: " + i + " " +   secondHiddenLayer.get(i));
//		}
//		System.out.println("---------------------------");
		for(int i = 0; i < outputNeuralNet.size(); i++) {
			System.out.println("Final OutPut: " + outputNeuralNet.get(i));
		}
		System.out.println("---------------------------");
		
		return outputNeuralNet;
	}
	
	private Double sigmoidNeuron(ArrayList<Double> we,Double bias) {
		
		Double x = 0d;
		
		for(int i = 0; i < we.size(); i++) {
			x += we.get(i);
		}
		
		x += bias;
		Double neuron = 1/(1 + Math.exp(-x));
		
		return neuron;
	}
	
	private ArrayList<Double> getNeuronLayer(ArrayList<Double> input, int numberOfNeuron,int startNeuron, int n) {
		
		ArrayList<Double> we = new ArrayList<Double>();
		ArrayList<Double> neuronLayer = new ArrayList<Double>();
		
		int j = 0;
		while(startNeuron < numberOfNeuron) {
			while(j < input.size()) {
				we.add((( ((this.car.getGenome().get(this.w) * 1000000) * input.get(j)) / n)));
				j++;
				this.w++;
			}
			j = 0;
			neuronLayer.add(this.sigmoidNeuron(we, this.bias[startNeuron]));
			startNeuron++;
		}
		return neuronLayer;
	}

}
