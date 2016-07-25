package io.github.marcondesnjr.bdnc.primeiroproj.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.vividsolutions.jts.geom.Point;
import java.time.LocalDate;

/**
 * @brief Classe Incidente
 * @author José Marcondes do Nascimento Junior
 * @date   21/07/2016
 */
@Entity
public class Incidente implements Serializable {
    
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoIncidente tipo;
    @Enumerated(EnumType.STRING)
    private TipoPresenca presenca;
    @Column(columnDefinition="Geometry", nullable = true)
    private Point localizacao;
    private String info;
    private LocalDate data;

    public Incidente() {
    }

    public Incidente(TipoIncidente tipo, TipoPresenca presenca, Point local, String info, LocalDate data) {
        this.tipo = tipo;
        this.presenca = presenca;
        this.localizacao = local;
        this.info = info;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoIncidente getTipo() {
        return tipo;
    }

    public void setTipo(TipoIncidente tipo) {
        this.tipo = tipo;
    }

    public TipoPresenca getPresenca() {
        return presenca;
    }

    public void setPresenca(TipoPresenca presenca) {
        this.presenca = presenca;
    }

    public Point getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Point localizacao) {
        this.localizacao = localizacao;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    
    

    
    
}
