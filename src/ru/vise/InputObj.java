package ru.vise;

public class InputObj {
    private double capital;
    private String distrib;
    private double mu;
    private double sigma;
    private double k;
    private int iteration;
    private int stepNum;
    private int peopleCount;
    private double alpha;
    private int start;
    private int finish;
    private int step;
    private Double majorityThreshold;

    public Double getMajorityThreshold() {
        return majorityThreshold;
    }

    public void setMajorityThreshold(Double majorityThreshold) {
        this.majorityThreshold = majorityThreshold;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public String getDistrib() {
        return distrib;
    }

    public void setDistrib(String distrib) {
        this.distrib = distrib;
    }

    public double getMu() {
        return mu;
    }

    public void setMu(double mu) {
        this.mu = mu;
    }

    public double getSigma() {
        return sigma;
    }

    public void setSigma(double sigma) {
        this.sigma = sigma;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public int getStepNum() {
        return stepNum;
    }

    public void setStepNum(int stepNum) {
        this.stepNum = stepNum;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InputObj inputObj = (InputObj) o;

        if (Double.compare(inputObj.capital, capital) != 0) return false;
        if (Double.compare(inputObj.mu, mu) != 0) return false;
        if (Double.compare(inputObj.sigma, sigma) != 0) return false;
        if (Double.compare(inputObj.k, k) != 0) return false;
        if (iteration != inputObj.iteration) return false;
        if (stepNum != inputObj.stepNum) return false;
        if (peopleCount != inputObj.peopleCount) return false;
        if (Double.compare(inputObj.alpha, alpha) != 0) return false;
        if (start != inputObj.start) return false;
        if (finish != inputObj.finish) return false;
        if (step != inputObj.step) return false;
        if (!distrib.equals(inputObj.distrib)) return false;
        return majorityThreshold.equals(inputObj.majorityThreshold);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(capital);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + distrib.hashCode();
        temp = Double.doubleToLongBits(mu);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(sigma);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(k);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + iteration;
        result = 31 * result + stepNum;
        result = 31 * result + peopleCount;
        temp = Double.doubleToLongBits(alpha);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + start;
        result = 31 * result + finish;
        result = 31 * result + step;
        result = 31 * result + majorityThreshold.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "InputObj{" +
                "capital=" + capital +
                ", distrib='" + distrib + '\'' +
                ", mu=" + mu +
                ", sigma=" + sigma +
                ", k=" + k +
                ", iteration=" + iteration +
                ", stepNum=" + stepNum +
                ", peopleCount=" + peopleCount +
                ", alpha=" + alpha +
                ", start=" + start +
                ", finish=" + finish +
                ", step=" + step +
                ", majorityThreshold=" + majorityThreshold +
                '}';
    }

}
