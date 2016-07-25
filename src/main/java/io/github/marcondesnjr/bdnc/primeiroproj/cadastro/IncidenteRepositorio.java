package io.github.marcondesnjr.bdnc.primeiroproj.cadastro;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import io.github.marcondesnjr.bdnc.primeiroproj.entidades.Incidente;
import io.github.marcondesnjr.bdnc.primeiroproj.entidades.TipoIncidente;
import io.github.marcondesnjr.bdnc.primeiroproj.entidades.TipoPresenca;
import io.github.marcondesnjr.bdnc.primeiroproj.entidades.Usuario;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.postgis.Geometry;
import org.postgis.PGgeometry;
import org.postgresql.PGConnection;
import org.postgresql.util.PGobject;

/**
 * @brief Classe IncidenteRepositorio
 * @author José Marcondes do Nascimento Junior
 * @date 24/07/2016
 */
public class IncidenteRepositorio {

    @Inject
    private DataSource dataSource;
    private QueryRunner queryRunner;

    @PostConstruct
    private void init(){
        
            queryRunner = new QueryRunner(dataSource);
            
        
    }

    public void salvar(Usuario usr, Incidente incidente) throws SQLException {
        double x = incidente.getLocalizacao().getCoordinate().x;
        double y = incidente.getLocalizacao().getCoordinate().y;
        String sql = "INSERT INTO incidente(data, info, localizacao, presenca, tipo, incidentes_email) VALUES (?, ?, ST_GeomFromText('POINT(" + x + " " + y + ")', 4326), ?, ?, ?)";
        queryRunner.update(sql, Date.valueOf(incidente.getData()), incidente.getInfo(), incidente.getPresenca().name(), incidente.getTipo().name(),
                usr == null ? null : usr.getEmail());
    }

    public List<Incidente> recuperarTodos() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM INCIDENTE";
        return queryRunner.query(sql, new ResultSetHandler<List<Incidente>>() {
            @Override
            public List<Incidente> handle(ResultSet rs) throws SQLException {
                List<Incidente> incidentes = new ArrayList<>();
                while (rs.next()) {
                    LocalDate data = rs.getDate(2).toLocalDate();
                    String info = rs.getString(3);
                    String localStr = ((PGobject)rs.getObject(4)).getValue();
                    PGgeometry local = new PGgeometry(localStr);
                    TipoPresenca tipoPresenca = TipoPresenca.valueOf(rs.getString(5));
                    TipoIncidente tipoIncidente = TipoIncidente.valueOf(rs.getString(6));
                    Point ponto = new GeometryFactory().createPoint(new Coordinate(local.getGeometry().getFirstPoint().x, 
                    local.getGeometry().getFirstPoint().y));
                    Incidente incidente = new Incidente(tipoIncidente, tipoPresenca, ponto, info, data);
                    incidentes.add(incidente);
                }
                return incidentes;
            }
        });
    }

}
