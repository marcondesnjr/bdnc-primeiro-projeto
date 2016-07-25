package io.github.marcondesnjr.bdnc.primeiroproj.managedbean;

import io.github.marcondesnjr.bdnc.primeiroproj.cadastro.IncidenteCadastro;
import io.github.marcondesnjr.bdnc.primeiroproj.entidades.TipoPresenca;
import io.github.marcondesnjr.bdnc.primeiroproj.entidades.Usuario;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 * @brief Classe CadastrarIncidenteMB
 * @author José Marcondes do Nascimento Junior
 * @date 22/07/2016
 */
@ManagedBean(name = "cadastroIncidenteMB")
@RequestScoped
public class CadastroIncidenteMB {

    private String localizacao;
    private String tipo;
    private boolean vitima;
    private boolean anonimo;
    private String info;
    private Date data;

    @Inject
    private IncidenteCadastro incidenteCadastro;
    @ManagedProperty(value = "#{loginMB}")
    private LoginMB loginMB;
    
    

    public CadastroIncidenteMB() {
        data = new Date();
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isVitima() {
        return vitima;
    }

    public void setVitima(boolean vitima) {
        this.vitima = vitima;
    }

    public boolean isAnonimo() {
        return anonimo;
    }

    public void setAnonimo(boolean anonimo) {
        this.anonimo = anonimo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public IncidenteCadastro getIncidenteCadastro() {
        return incidenteCadastro;
    }

    public void setIncidenteCadastro(IncidenteCadastro incidenteCadastro) {
        this.incidenteCadastro = incidenteCadastro;
    }

    public LoginMB getLoginMB() {
        return loginMB;
    }

    public void setLoginMB(LoginMB loginMB) {
        this.loginMB = loginMB;
    }
    
    
    public void cadastrarIncidente() throws SQLException {
        LocalDate data = this.data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Usuario usr = anonimo?null:loginMB.getUsuarioLogado();
        incidenteCadastro.persistir(tipo, vitima ? "VITIMA" : "TESTEMUNHA", localizacao, info, data,usr);
        localizacao = localizacao.substring(1, localizacao.length()-1);
        String[] local = localizacao.split(",");
        String localjs = "new google.maps.LatLng("+local[0].trim()+","+local[1].trim()+")";
        String script = "placemarker("+localjs+");"
                + "PF('addIncidenteDialog').hide();";
        RequestContext.getCurrentInstance().execute(script);
    }

}
