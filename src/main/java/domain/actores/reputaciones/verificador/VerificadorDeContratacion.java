package domain.actores.reputaciones.verificador;

public class VerificadorDeContratacion {
    private Integer peridoDeMaximosEnSemanas;
    private Integer maxCantVecesPorPeriodo;
    private Integer cantVecesContratadoEnPeriodo;

    public VerificadorDeContratacion(Integer peridoDeMaximosEnSemanas, Integer maxCantVecesPorPeriodo, Integer cantVecesContratadoEnPeriodo){
        this.peridoDeMaximosEnSemanas = peridoDeMaximosEnSemanas;
        this.maxCantVecesPorPeriodo = maxCantVecesPorPeriodo;
        this.cantVecesContratadoEnPeriodo = cantVecesContratadoEnPeriodo;
    }

    public Boolean prestadorPuedeSerContratado(Integer cantSemanasDesdeElUltimoTrabajo){
        return estaFueraDelPeriodo(cantSemanasDesdeElUltimoTrabajo) || estaDentroDelPeriodoPeroNoSuperaLimite(cantSemanasDesdeElUltimoTrabajo);
    }

    public Integer valorParaContadorDeContrataciones(Integer cantSemanasDesdeElUltimTrabajo){
        return estaDentroDelPeriodoPeroNoSuperaLimite(cantSemanasDesdeElUltimTrabajo)?
                this.cantVecesContratadoEnPeriodo + 1 : 1;
    }

    private boolean estaDentroDelPeriodoPeroNoSuperaLimite(Integer cantSemanasDesdeElUltimoTrabajo) {
        return cantSemanasDesdeElUltimoTrabajo < peridoDeMaximosEnSemanas &&
                this.cantVecesContratadoEnPeriodo < maxCantVecesPorPeriodo;
    }

    private Boolean estaFueraDelPeriodo(Integer cantSemanasDesdeElUltimoTrabajo){
        return cantSemanasDesdeElUltimoTrabajo >= peridoDeMaximosEnSemanas;
    }
}
