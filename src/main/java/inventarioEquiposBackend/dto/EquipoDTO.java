package inventarioEquiposBackend.dto;



import java.util.Date;

public class EquipoDTO {
    private Long id;
    private String nombreEquipo;
    private String descripcion;
    private String tipo;
    private String modelo;
    private String marca;
    private String serie;
    private String ubicacionDelEquipo;
    private String utilizacion;
    private String recibidoPor;
    private String proveedor;
    private String ordenDeCompra;
    private String factura;
    private Date fechaDeCompra;
    private Date fechaFinGarantia;
    private String garantia;
    private Double precio;
    private String procesador;
    private int memoriaRamGB;
    private int almacenamientoGB;
    private String tipoAlmacenamiento;
    private String placaBase;
    private int fuentePoderWatts;
    private String tarjetaGrafica;
    private boolean tieneTarjetaRed;
    private boolean tieneTarjetaSonido;
    private String gabinete;
    private String perifericosEntrada;
    private String perifericosSalida;
    private String componentes;
    private String accesorios;
    private String sistemaOperativo;
    private String versionSO;
    private String driversInstalados;
    private String programasInstalados;
    private String utilidadesSistema;
    private String direccionIP;
    private String direccionMAC;

    // Constructor vacío
    public EquipoDTO() {
    }

    // Constructor completo (opcional, puedes agregar solo los campos que usarás)
    public EquipoDTO(Long id, String nombreEquipo, String descripcion, String tipo, String modelo, String marca,
                     String serie, String ubicacionDelEquipo, String utilizacion, String recibidoPor,
                     String proveedor, String ordenDeCompra, String factura, Date fechaDeCompra,
                     Date fechaFinGarantia, String garantia, Double precio, String procesador,
                     int memoriaRamGB, int almacenamientoGB, String tipoAlmacenamiento, String placaBase,
                     int fuentePoderWatts, String tarjetaGrafica, boolean tieneTarjetaRed, boolean tieneTarjetaSonido,
                     String gabinete, String perifericosEntrada, String perifericosSalida, String componentes,
                     String accesorios, String sistemaOperativo, String versionSO, String driversInstalados,
                     String programasInstalados, String utilidadesSistema, String direccionIP, String direccionMAC) {
        this.id = id;
        this.nombreEquipo = nombreEquipo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.modelo = modelo;
        this.marca = marca;
        this.serie = serie;
        this.ubicacionDelEquipo = ubicacionDelEquipo;
        this.utilizacion = utilizacion;
        this.recibidoPor = recibidoPor;
        this.proveedor = proveedor;
        this.ordenDeCompra = ordenDeCompra;
        this.factura = factura;
        this.fechaDeCompra = fechaDeCompra;
        this.fechaFinGarantia = fechaFinGarantia;
        this.garantia = garantia;
        this.precio = precio;
        this.procesador = procesador;
        this.memoriaRamGB = memoriaRamGB;
        this.almacenamientoGB = almacenamientoGB;
        this.tipoAlmacenamiento = tipoAlmacenamiento;
        this.placaBase = placaBase;
        this.fuentePoderWatts = fuentePoderWatts;
        this.tarjetaGrafica = tarjetaGrafica;
        this.tieneTarjetaRed = tieneTarjetaRed;
        this.tieneTarjetaSonido = tieneTarjetaSonido;
        this.gabinete = gabinete;
        this.perifericosEntrada = perifericosEntrada;
        this.perifericosSalida = perifericosSalida;
        this.componentes = componentes;
        this.accesorios = accesorios;
        this.sistemaOperativo = sistemaOperativo;
        this.versionSO = versionSO;
        this.driversInstalados = driversInstalados;
        this.programasInstalados = programasInstalados;
        this.utilidadesSistema = utilidadesSistema;
        this.direccionIP = direccionIP;
        this.direccionMAC = direccionMAC;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getUbicacionDelEquipo() {
        return ubicacionDelEquipo;
    }

    public void setUbicacionDelEquipo(String ubicacionDelEquipo) {
        this.ubicacionDelEquipo = ubicacionDelEquipo;
    }

    public String getUtilizacion() {
        return utilizacion;
    }

    public void setUtilizacion(String utilizacion) {
        this.utilizacion = utilizacion;
    }

    public String getRecibidoPor() {
        return recibidoPor;
    }

    public void setRecibidoPor(String recibidoPor) {
        this.recibidoPor = recibidoPor;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getOrdenDeCompra() {
        return ordenDeCompra;
    }

    public void setOrdenDeCompra(String ordenDeCompra) {
        this.ordenDeCompra = ordenDeCompra;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public Date getFechaDeCompra() {
        return fechaDeCompra;
    }

    public void setFechaDeCompra(Date fechaDeCompra) {
        this.fechaDeCompra = fechaDeCompra;
    }

    public Date getFechaFinGarantia() {
        return fechaFinGarantia;
    }

    public void setFechaFinGarantia(Date fechaFinGarantia) {
        this.fechaFinGarantia = fechaFinGarantia;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public int getMemoriaRamGB() {
        return memoriaRamGB;
    }

    public void setMemoriaRamGB(int memoriaRamGB) {
        this.memoriaRamGB = memoriaRamGB;
    }

    public int getAlmacenamientoGB() {
        return almacenamientoGB;
    }

    public void setAlmacenamientoGB(int almacenamientoGB) {
        this.almacenamientoGB = almacenamientoGB;
    }

    public String getTipoAlmacenamiento() {
        return tipoAlmacenamiento;
    }

    public void setTipoAlmacenamiento(String tipoAlmacenamiento) {
        this.tipoAlmacenamiento = tipoAlmacenamiento;
    }

    public String getPlacaBase() {
        return placaBase;
    }

    public void setPlacaBase(String placaBase) {
        this.placaBase = placaBase;
    }

    public int getFuentePoderWatts() {
        return fuentePoderWatts;
    }

    public void setFuentePoderWatts(int fuentePoderWatts) {
        this.fuentePoderWatts = fuentePoderWatts;
    }

    public String getTarjetaGrafica() {
        return tarjetaGrafica;
    }

    public void setTarjetaGrafica(String tarjetaGrafica) {
        this.tarjetaGrafica = tarjetaGrafica;
    }

    public boolean isTieneTarjetaRed() {
        return tieneTarjetaRed;
    }

    public void setTieneTarjetaRed(boolean tieneTarjetaRed) {
        this.tieneTarjetaRed = tieneTarjetaRed;
    }

    public boolean isTieneTarjetaSonido() {
        return tieneTarjetaSonido;
    }

    public void setTieneTarjetaSonido(boolean tieneTarjetaSonido) {
        this.tieneTarjetaSonido = tieneTarjetaSonido;
    }

    public String getGabinete() {
        return gabinete;
    }

    public void setGabinete(String gabinete) {
        this.gabinete = gabinete;
    }

    public String getPerifericosEntrada() {
        return perifericosEntrada;
    }

    public void setPerifericosEntrada(String perifericosEntrada) {
        this.perifericosEntrada = perifericosEntrada;
    }

    public String getPerifericosSalida() {
        return perifericosSalida;
    }

    public void setPerifericosSalida(String perifericosSalida) {
        this.perifericosSalida = perifericosSalida;
    }

    public String getComponentes() {
        return componentes;
    }

    public void setComponentes(String componentes) {
        this.componentes = componentes;
    }

    public String getAccesorios() {
        return accesorios;
    }

    public void setAccesorios(String accesorios) {
        this.accesorios = accesorios;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public String getVersionSO() {
        return versionSO;
    }

    public void setVersionSO(String versionSO) {
        this.versionSO = versionSO;
    }

    public String getDriversInstalados() {
        return driversInstalados;
    }

    public void setDriversInstalados(String driversInstalados) {
        this.driversInstalados = driversInstalados;
    }

    public String getProgramasInstalados() {
        return programasInstalados;
    }

    public void setProgramasInstalados(String programasInstalados) {
        this.programasInstalados = programasInstalados;
    }

    public String getUtilidadesSistema() {
        return utilidadesSistema;
    }

    public void setUtilidadesSistema(String utilidadesSistema) {
        this.utilidadesSistema = utilidadesSistema;
    }

    public String getDireccionIP() {
        return direccionIP;
    }

    public void setDireccionIP(String direccionIP) {
        this.direccionIP = direccionIP;
    }

    public String getDireccionMAC() {
        return direccionMAC;
    }

    public void setDireccionMAC(String direccionMAC) {
        this.direccionMAC = direccionMAC;
    }
}
