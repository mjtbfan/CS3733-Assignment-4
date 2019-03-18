package converter.tests;

import converter.ElbonianArabicConverter;
import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the ElbonianArabicConverter class.
 */
public class ConverterTests {

    @Test(expected = MalformedNumberException.class)
    public void toElbonianTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("M M");
        converter.toElbonian();
       //assertEquals(converter.toElbonian(), "DCCCLXXXVIII");
    }

    @Test (expected = MalformedNumberException.class)
    public void toArabaicTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("M M");
       // assertEquals(converter.toArabic(),"");
       converter.toArabic();
    }
    /*
    @Test(expected = ValueOutOfBoundsException.class)
    public void malformedNumberTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("8888");
        converter.toElbonian();
    }
    @Test(expected = MalformedNumberException.class)
    public void valueOutOfBoundsTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("MMDemw");
        converter.toArabic();
    }
    */
    
}
