package school.sptech;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

public class LeituraMetrica {
    @JsonProperty("Quando foi Coletado")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataColeta;
    @JsonProperty("cpu total(%)")
    private Double cpu;
    @JsonProperty("ram(%)")
    private Double ram;
    @JsonProperty("perda pacotes")
    private Double perdaPacotes;
    @JsonProperty("disco(%)")
    private Double disco;

    public LeituraMetrica() {
    }
    public LeituraMetrica(LocalDateTime dataColeta, Double cpu, Double ram, Double perdaPacotes, Double disco) {
        this.dataColeta = dataColeta;
        this.cpu = cpu;
        this.ram = ram;
        this.perdaPacotes = perdaPacotes;
        this.disco = disco;
    }

    public LocalDateTime getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDateTime dataColeta) {
        this.dataColeta = dataColeta;
    }

    public Double getCpu() {
        return cpu;
    }

    public void setCpu(Double cpu) {
        this.cpu = cpu;
    }

    public Double getRam() {
        return ram;
    }

    public void setRam(Double ram) {
        this.ram = ram;
    }

    public Double getPerdaPacotes() {
        return perdaPacotes;
    }

    public void setPerdaPacotes(Double perdaPacotes) {
        this.perdaPacotes = perdaPacotes;
    }

    public Double getDisco() {
        return disco;
    }

    public void setDisco(Double disco) {
        this.disco = disco;
    }
}
