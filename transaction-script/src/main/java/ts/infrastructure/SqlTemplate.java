package ts.infrastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;

public class SqlTemplate {
    
    public String getTemplate(Class<?> daoClass, String methodName) {
        String parentPackageName = this.resolveParentPackageName(daoClass);
        String daoName = daoClass.getSimpleName();
        String templateName = "/sql/template/" + parentPackageName + "/" + daoName + "_" + methodName + ".sql";
        
        try (InputStream in = this.getClass().getResourceAsStream(templateName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            
            String line;
            StringBuilder sql = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sql.append(line).append("\n");
            }
            
            return sql.toString();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
    private String resolveParentPackageName(Class<?> daoClass) {
        String packageName = daoClass.getPackage().toString();
        String[] packageNames = packageName.split("\\.");
        int packageLength = packageNames.length;
        return packageNames[packageLength - 1];
    }
}
