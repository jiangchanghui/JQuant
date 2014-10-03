/*
 * Copyright 2014 davidherod.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package au.com.jquant.analytics.indicators;

import au.com.jquant.asset.Interval;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davidherod
 */
public class BollingerBands {

    private double upperBand;
    private double middleBand;
    private double lowerBand;
    private int movingAverage;
    private double upperStdFactor;
    private double lowerStdFactor;
    private List<Interval> intervals;

    public BollingerBands() {
    }

    // Constructor used to retrieve values created from
    public BollingerBands(double upperBand, double middleBand, double lowerBand) {
        this.upperBand = upperBand;
        this.middleBand = middleBand;
        this.lowerBand = lowerBand;
    }

    public BollingerBands(int movingAverage, double upperStdFactor, double lowerStdFactor, List<Interval> intervals) {
        this.movingAverage = movingAverage;
        this.upperStdFactor = upperStdFactor;
        this.lowerStdFactor = lowerStdFactor;
        this.intervals = intervals;
    }

    public double getUpperBand() {
        return upperBand;
    }

    public double getMiddleBand() {
        return middleBand;
    }

    public double getLowerBand() {
        return lowerBand;
    }

    // TODO add support for open, high and low
    public double calculateUpperBand(double factor, List<Interval> intervals) {
        double std = 0;
        double average = new MovingAverage().getSimpleMovingAverage(intervals);
        for (int i = 0; i < intervals.size(); i++) {
            std += (intervals.get(i).getClose() - average) * (intervals.get(i).getClose() - average);
        }
        return average + (Math.sqrt(std / intervals.size()) * factor);
    }

    public double calculateMiddleBand() {
        return new MovingAverage().getSimpleMovingAverage(intervals);
    }

    // TODO add support for open, high and low
    public double calculateLowerBand(double factor, List<Interval> intervals) {
        double std = 0;
        double average = new MovingAverage().getSimpleMovingAverage(intervals);
        for (int i = 0; i < intervals.size(); i++) {
            std += (intervals.get(i).getClose() - average) * (intervals.get(i).getClose() - average);
        }
        return +average - (Math.sqrt(std / intervals.size()) * factor);
    }

    public List<BollingerBands> getBollingerBands(int movingAverage, double factorUpper, double factorLower, List<Interval> intervals) {
        List<BollingerBands> bands = new ArrayList<>();

        // Set periods that are less then value of moving average as null.
        for (int i = 0; i < movingAverage; i++) {
            bands.set(i, null);
        }

        // Set bollinger band values   
        for (int i = movingAverage; i < intervals.size(); i++) {
            BollingerBands band = new BollingerBands(calculateUpperBand(factorUpper, intervals.subList(i, i + movingAverage)), calculateMiddleBand(), calculateLowerBand(factorLower, intervals.subList(i, i + movingAverage)));
            bands.add(band);
        }
        return bands;
    }
}
