/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.DTO;

import com.allyrent.entidades.Usuario;
import com.allyrent.utility.ImageToArray;
import java.util.Date;

/**
 *
 * @author Jorge
 */
public class UsuarioDTO {

    private Integer idUsuario;

    private String dni;

    private String nombre;

    private String apellido;

    private String telefono;

    private Date fechaNacimiento;

    private int idRol;

    private String nombreRol;

    private int idSexo;

    private String nombreSexo;

    private int idTipoDocumento;

    private String nombreTipoDocumento;

    private int idCiudad;

    private String nombreCiudad;

    private String rutaFoto;

    public LoginDTO idLogin;
    
    public int numeroPublicaciones;
    
    public int numeroVehiculos;

    public int getNumeroPublicaciones() {
        return numeroPublicaciones;
    }

    public void setNumeroPublicaciones(int numeroPublicaciones) {
        this.numeroPublicaciones = numeroPublicaciones;
    }

    public int getNumeroVehiculos() {
        return numeroVehiculos;
    }

    public void setNumeroVehiculos(int numeroVehiculos) {
        this.numeroVehiculos = numeroVehiculos;
    }

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario user) {
        this.idUsuario = user.getIdUsuario();
        this.nombre = user.getNombre();
        this.apellido = user.getApellido();
        this.telefono = user.getTelefono();
        this.dni = user.getDni();
        this.fechaNacimiento = user.getFechaNacimiento();
        this.rutaFoto = ImageToArray.converseByteArrayToImage(user.getRutaFoto());
        if (user.getIdLogin() != null) {
            LoginDTO log = new LoginDTO();
            log.setContrasena(user.getIdLogin().getContrasena());
            log.setCorreo(user.getIdLogin().getCorreo());
            log.setUltimaConexion(user.getIdLogin().getUltimaConexion());
            log.setIdLogin(user.getIdLogin().getIdLogin());
            log.setEstado(user.getIdLogin().getEstado());
            this.idLogin = log;
        }
        if (user.getIdRol() != null) {
            this.idRol = user.getIdRol().getIdRol();
            this.nombreRol = user.getIdRol().getNombreRol();
        }
        if (user.getIdCiudad() != null) {
            this.idCiudad = user.getIdCiudad().getIdCiudad();
            this.nombreCiudad = user.getIdCiudad().getNombreCiudad();
        }
        if (user.getIdTipoDocumento()!=null){
            this.idTipoDocumento = user.getIdTipoDocumento().getIdDataType();
            this.nombreTipoDocumento = user.getIdTipoDocumento().getNombreDataType();
        }
        if (user.getIdSexo()!= null){
            this.idSexo = user.getIdSexo().getIdDataType();
            this.nombreSexo = user.getIdSexo().getNombreDataType();
        }
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getNombreSexo() {
        return nombreSexo;
    }

    public void setNombreSexo(String nombreSexo) {
        this.nombreSexo = nombreSexo;
    }

    public String getNombreTipoDocumento() {
        return nombreTipoDocumento;
    }

    public void setNombreTipoDocumento(String nombreTipoDocumento) {
        this.nombreTipoDocumento = nombreTipoDocumento;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public LoginDTO getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(LoginDTO idLogin) {
        this.idLogin = idLogin;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(int idSexo) {
        this.idSexo = idSexo;
    }

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

}
