package db.migration;

import configuration.DatabaseConfiguration;
import org.flywaydb.core.Flyway;

/**
 * Created by anatarajan on 7/10/16.
 */
public class FlywayMigration {
    private static Flyway flywayInstance;

    public static Flyway getFlywayInstance(){
        if(flywayInstance == null){
            flywayInstance = new Flyway();
        }
        return flywayInstance;
    }

    public static void main(String args[]){
        getFlywayInstance();

        DatabaseConfiguration.getInstance().setPropertiesFile("application.properties");
        flywayInstance.setDataSource(DatabaseConfiguration.getInstance().getDataSource());

        flywayInstance.setInitOnMigrate(true);
        flywayInstance.migrate();
    }
}
