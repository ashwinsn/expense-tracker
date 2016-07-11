package configuration;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;

/**
 * Created by anatarajan on 7/10/16.
 */
public class DatabaseConfiguration extends Configuration {

    private static DatabaseConfiguration databaseConfiguration;

    private static Configuration.KeyWithValueTypeT<String> dataSourceClass;
    private static Configuration.KeyWithValueTypeT<String> databaseUrl;
    private static Configuration.KeyWithValueTypeT<String> hostName;
    private static Configuration.KeyWithValueTypeT<String> databaseName;
    private static Configuration.KeyWithValueTypeT<String> userName;
    private static Configuration.KeyWithValueTypeT<String> password;


    private DatabaseConfiguration(){
        super();
    }

    public static DatabaseConfiguration getInstance(){
        if(databaseConfiguration == null){
            databaseConfiguration = new DatabaseConfiguration();

            databaseName = new Configuration.KeyWithValueTypeT<String>("mysql.database.name");
            databaseUrl = new Configuration.KeyWithValueTypeT<String>("mysql.url");
            hostName = new Configuration.KeyWithValueTypeT<String>("mysql.host.name");
            dataSourceClass = new Configuration.KeyWithValueTypeT<String>("mysql.data.source.class");
            userName = new Configuration.KeyWithValueTypeT<String>("mysql.user.name");
            password = new Configuration.KeyWithValueTypeT<String>("mysql.database.password");
        }
        return databaseConfiguration;
    }

    public void setPropertiesFile(String propertiesFile){
        setPropertyFile(propertiesFile);
    }

    public DataSource getDataSource(){
        loadFromFile();

        MysqlDataSource mysqlDataSource= new MysqlDataSource();
        mysqlDataSource.setDatabaseName(get(databaseName,null));
        mysqlDataSource.setUser(get(userName, null));
        mysqlDataSource.setPassword(get(password,null));
        mysqlDataSource.setURL(get(databaseUrl, null));
        return mysqlDataSource;
    }

}
