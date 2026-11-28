package school.sptech;

public class mediaMetricas {
    private Integer hora;
    private Double mediaCpu;
    private Double mediaRam;
    private Double mediaPerdaPacotes;
    private Double mediaDisco;

    public mediaMetricas(Integer hora, Double mediaCpu, Double mediaRam, Double mediaPerdaPacotes, Double mediaDisco) {
        this.hora = hora;
        this.mediaCpu = mediaCpu;
        this.mediaRam = mediaRam;
        this.mediaPerdaPacotes = mediaPerdaPacotes;
        this.mediaDisco = mediaDisco;
    }

    public Integer getHora() {
        return hora;
    }

    public Double getMediaCpu() {
        return mediaCpu;
    }

    public Double getMediaRam() {
        return mediaRam;
    }

    public Double getMediaPerdaPacotes() {
        return mediaPerdaPacotes;
    }

    public Double getMediaDisco() {
        return mediaDisco;
    }
}
