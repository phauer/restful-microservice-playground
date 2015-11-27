package de.philipphauer.articleservice;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by hauer on 27.11.2015.
 */
public class CalculatorTest {

    @Test
    public void add(){
        Calculator calc = new Calculator();
        assertThat(calc.add(1,2)).isEqualTo(3);
    }
}