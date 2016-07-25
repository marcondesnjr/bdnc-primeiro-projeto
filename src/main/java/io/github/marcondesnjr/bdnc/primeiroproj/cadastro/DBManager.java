package io.github.marcondesnjr.bdnc.primeiroproj.cadastro;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.postgresql.PGConnection;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class DBManager {

    public DBManager() {
    }
    
    @Produces
    @ApplicationScoped
    public DataSource getDataSource() throws ClassNotFoundException, IOException, SQLException {
        Properties prop = new Properties();
        prop.load(this.getClass().getResourceAsStream("/database/db.properties"));
        Class.forName(prop.getProperty("driver"));  
        DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory(prop.getProperty("url"),prop);
        PoolableConnectionFactory poolableCFac = new PoolableConnectionFactory(connectionFactory, null);
        ObjectPool<PoolableConnection> objPool = new GenericObjectPool<>(poolableCFac);
        poolableCFac.setPool(objPool);
        PoolingDataSource<PoolableConnection> poolDS = new PoolingDataSource<>(objPool);
//        PGConnection pgConnection = poolDS.getConnection().unwrap(PGConnection.class);
//        pgConnection.addDataType("geometry", Class.forName("org.postgis.PGgeometry"));
        return poolDS;
    }    

}
