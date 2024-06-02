package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {
    //DataProviders 1

    @DataProvider(name="LoginData")
    public static Object[][] getData() throws IOException {
        String path = ".\\testData\\Opencart_LoginData.xlsx";
        ExcelUtility xlUtil = new ExcelUtility(path);
        int totalRows = xlUtil.getRowCount("Sheet1");
        int totalColumns = xlUtil.getCellCount("Sheet1", 1);

        Object [][] loginData = new Object[totalRows][totalColumns];
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                loginData[i-1][j] = xlUtil.getCellData("Sheet1", i, j);
            }
        } return loginData; //returning two-dimension array.
    }

    //dataProvider 2
    //datsProvider 3 etc.

}
