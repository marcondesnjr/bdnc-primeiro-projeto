package io.github.marcondesnjr.bdnc.primeiroproj.managedbean;

import io.github.marcondesnjr.bdnc.primeiroproj.cadastro.IncidenteCadastro;
import io.github.marcondesnjr.bdnc.primeiroproj.entidades.Incidente;
import io.github.marcondesnjr.bdnc.primeiroproj.entidades.TipoIncidente;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
@ManagedBean(name = "pesquisaMarcadoresMB")
@Dependent
public class PesquisaMarcadoresMB {
    
    private Date primeiraData;
    private Date segundaData;
    private String tipo;
    
    @Inject
    private IncidenteCadastro incidenteCadastro;

    /**
     * Creates a new instance of PesquisaMarcadores
     */
    public PesquisaMarcadoresMB() {
        primeiraData = new Date();
        segundaData = new Date();
    }
    
    public void pesquisar() throws SQLException{
        
        LocalDate pData = primeiraData.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate sData = segundaData.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        List<Incidente> result = incidenteCadastro.findByDataAndTipo(pData, sData, TipoIncidente.valueOf(tipo));
        RequestContext.getCurrentInstance().execute("deleteMarkers()");
        for (Incidente incidente : result) {
            String localjs = "new google.maps.LatLng(" + incidente.getLocalizacao().getCoordinate().x + "," + incidente.getLocalizacao().getCoordinate().y + ")";
            String script = "placemarker(" + localjs + ");";
            RequestContext.getCurrentInstance().execute(script);
            
        }
    }

    public Date getPrimeiraData() {
        return primeiraData;
    }

    public void setPrimeiraData(Date primeiraData) {
        this.primeiraData = primeiraData;
    }

    public Date getSegundaData() {
        return segundaData;
    }

    public void setSegundaData(Date segundaData) {
        this.segundaData = segundaData;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public IncidenteCadastro getIncidenteCadastro() {
        return incidenteCadastro;
    }

    public void setIncidenteCadastro(IncidenteCadastro incidenteCadastro) {
        this.incidenteCadastro = incidenteCadastro;
    }
    
    
    
}
