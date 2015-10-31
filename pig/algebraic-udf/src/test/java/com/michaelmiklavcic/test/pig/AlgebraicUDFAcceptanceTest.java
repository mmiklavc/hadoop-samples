package com.michaelmiklavcic.test.pig;

import java.io.File;

import org.adrianwalker.multilinestring.Multiline;
import org.apache.pig.pigunit.PigTest;
import org.junit.*;

import com.michaelmiklavcic.test.util.TestUtils;

public class AlgebraicUDFAcceptanceTest {

    private File tmpDir;

    @Before
    public void setUp() throws Exception {
        tmpDir = TestUtils.createTempDir(this.getClass().getName());
    }

    /**
     *raw = load '#INPUTFILE#' using PigStorage() as (num:int);
     *grouped = group raw all;
     *counted = foreach grouped generate com.michaelmiklavcic.pig.udf.COUNT(raw);
     */
    @Multiline private static String scriptAlgebraic;

    private static String in;

    private static String out;

    @Test
    public void returns_count_algebraic() throws Exception {
        in = buildInput();
        out = "(1000)";
        File pigScript = new File(tmpDir, "count.pig");
        File inData = new File(tmpDir, "in.csv");
        File outData = new File(tmpDir, "out.csv");
        scriptAlgebraic = scriptAlgebraic.replace("#INPUTFILE#", inData.getAbsolutePath());
        TestUtils.write(pigScript, scriptAlgebraic);
        TestUtils.write(inData, in);
        TestUtils.write(outData, out);
        PigTest test = new PigTest(pigScript.getAbsolutePath());
        test.assertOutput("counted", outData);
    }

    /**
     *raw = load '#INPUTFILE#' using PigStorage() as (num:int);
     *grouped = group raw all;
     *counted = foreach grouped generate com.michaelmiklavcic.pig.udf.COUNTNonAlgebraic(raw);
     */
    @Multiline private static String scriptNonAlgebraic;

    @Test
    public void returns_count_nonalgebraic() throws Exception {
        in = buildInput();
        out = "(1000)";
        File pigScript = new File(tmpDir, "count.pig");
        File inData = new File(tmpDir, "in.csv");
        File outData = new File(tmpDir, "out.csv");
        scriptNonAlgebraic = scriptNonAlgebraic.replace("#INPUTFILE#", inData.getAbsolutePath());
        TestUtils.write(pigScript, scriptNonAlgebraic);
        TestUtils.write(inData, in);
        TestUtils.write(outData, out);
        PigTest test = new PigTest(pigScript.getAbsolutePath());
        test.assertOutput("counted", outData);
    }

    private String buildInput() {
        StringBuilder sb = new StringBuilder();
        String delim = "";
        for (int i = 1; i < 1001; i++) {
            sb.append(delim);
            sb.append(i);
            delim = System.lineSeparator();
        }

        return sb.toString();
    }

}
