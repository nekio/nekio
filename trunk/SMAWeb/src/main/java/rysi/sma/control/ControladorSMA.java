package rysi.sma.control;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import rysi.sma.negocio.dao.CausaCierreTicketDAO;
import rysi.sma.negocio.dao.DepartamentoDAO;
import rysi.sma.negocio.dao.EstadoTicketDAO;
import rysi.sma.negocio.dao.NivelAtencionDAO;
import rysi.sma.negocio.dao.TicketDAO;
import rysi.sma.negocio.dao.TipoUsuarioDAO;
import rysi.sma.negocio.dao.TopicoAyudaDAO;
import rysi.sma.negocio.dao.UsuarioDAO;
import rysi.sma.negocio.dao.VSolicitudesTicketsDeptoDAO;
import rysi.sma.negocio.dao.VTiempoPromedioResolucionTicketsDAO;
import rysi.sma.negocio.herramientas.GestorJpaController;
import rysi.sma.negocio.modelo.Departamento;
import rysi.sma.negocio.modelo.NivelAtencion;
import rysi.sma.negocio.modelo.Ticket;
import rysi.sma.negocio.modelo.TipoUsuario;
import rysi.sma.negocio.modelo.TopicoAyuda;
import rysi.sma.negocio.modelo.Usuario;

/**
 *
 * @author Ivan
 */

@Controller
public class ControladorSMA {
    @Autowired
    TipoUsuarioDAO tipoUsuarioDAO;
    
    @Autowired
    UsuarioDAO usuarioDAO;    
    
    @Autowired
    TicketDAO ticketDAO;
    
    @Autowired
    CausaCierreTicketDAO causaCierreDAO;
    
    @Autowired
    DepartamentoDAO deptoDAO;
    
    @Autowired
    EstadoTicketDAO estadoTicketDAO;
    
    @Autowired
    NivelAtencionDAO nivelAtencionDAO;
    
    @Autowired
    TopicoAyudaDAO topicoAyudaDAO;
    
    @Autowired
    VSolicitudesTicketsDeptoDAO vSolicitudesTicketsDAO;
    
    @Autowired
    VTiempoPromedioResolucionTicketsDAO vResolucionTicketsDAO;
    
    /* Index */
    
    @RequestMapping("regresarIndex")
    public ModelAndView regresarIndex() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }
        
    /* Funcionalidades de Usuarios */
    
    @RequestMapping("listarUsuarios")
    public ModelAndView listarUsuarios() {
        ModelAndView mav = new ModelAndView("usuarios");
        mav.addObject("usuario", usuarioDAO.findAll());
        return mav;
    }
    
    @RequestMapping("nuevoUsuario")
    public ModelAndView nuevoUsuario() {
        ModelAndView mav = new ModelAndView("nuevoUsuario");
        mav.addObject("usuario", new Usuario());
        mav.addObject("fechaActualEnServidor", new Date());
        mav.addObject("tipoUsuario", tipoUsuarioDAO.findAll());
        return mav;
    }
    
    @RequestMapping(value = "agregarUsuario", method = RequestMethod.POST)
    public String agregarUsuario(@ModelAttribute Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("usuario", usuario);
            return "nuevoUsuario";
        }

        if (usuario.getIdTipoUsuario().getIdTipoUsuario() > 0) {
            TipoUsuario tipoUsuario = tipoUsuarioDAO.findOne(usuario.getIdTipoUsuario().getIdTipoUsuario());
            usuario.setIdTipoUsuario(tipoUsuario);
        }
        
        //usuarioDAO.save(usuario);
        //Ocupo mi propia opcion de guardar del JpaController, en vez del guardado por defecto del DAO
        GestorJpaController jpa = new GestorJpaController();
        jpa.getUsuarioJC().crearUsuario(
                usuario.getIdTipoUsuario(),
                usuario.getNombre(),
                usuario.getApellidoP(),
                usuario.getApellidoM(),
                usuario.getContacto(),
                usuario.getUsuario(),
                usuario.getPassword(),
                usuario.isActivo());

        return "redirect:/listarUsuarios";
    }
    
    @RequestMapping("editarEstadoUsuario")
    public ModelAndView editarEstadoUsuario() {
        ModelAndView mav = new ModelAndView("editarEstadoUsuario");
        mav.addObject("usuario", new Usuario());
        mav.addObject("usuarios", usuarioDAO.findAll());
        return mav;
    }
    
    
    @RequestMapping(value = "desactivarUsuario", method = RequestMethod.POST)
    public String desactivarUsuario(@ModelAttribute Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("usuario", usuario);
            return "nuevoUsuario";
        }
        
        GestorJpaController jpa = new GestorJpaController();
        jpa.getUsuarioJC().modificarUsuario(
                usuario.getIdUsuario(),
                null,
                null,
                null,
                null,
                null,
                null,
                false);
                
        return "redirect:/listarUsuarios";
    }
    
    /* Funcionalidades de Tickets */
    
    @RequestMapping("listarTickets")
    public ModelAndView listarTickets() {
        ModelAndView mav = new ModelAndView("tickets");
        mav.addObject("ticket", new Ticket());
        mav.addObject("tickets", ticketDAO.findAll());
        return mav;
    }
    
    @RequestMapping("nuevoTicket")
    public ModelAndView nuevoTicket() {
        ModelAndView mav = new ModelAndView("nuevoTicket");
        mav.addObject("ticket", new Ticket());
        mav.addObject("fechaActualEnServidor", new Date());
        mav.addObject("usuario", usuarioDAO.findAll());
        mav.addObject("depto", deptoDAO.findAll());
        mav.addObject("topicoAyuda", topicoAyudaDAO.findAll());
        mav.addObject("personalAtencion", usuarioDAO.findAll());
        mav.addObject("nivelAtencion", nivelAtencionDAO.findAll());
        mav.addObject("estado", estadoTicketDAO.findAll());
        mav.addObject("causaCierre", causaCierreDAO.findAll());
        return mav;
    }
    
    @RequestMapping(value = "agregarTicket", method = RequestMethod.POST)
    public String agregarTicket(@ModelAttribute Ticket ticket, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("ticket", ticket);
            return "nuevoTicket";
        }
        
        if (ticket.getIdUsuario().getIdUsuario() > 0) {
            Usuario usuario = usuarioDAO.findOne(ticket.getIdUsuario().getIdUsuario());
            ticket.setIdUsuario(usuario);
        }
        
        if (ticket.getIdDepto().getIdDepto() > 0) {
            Departamento depto = deptoDAO.findOne(ticket.getIdDepto().getIdDepto());
            ticket.setIdDepto(depto);
        }
        
        if (ticket.getIdTopicoAyuda().getIdTopicoAyuda() > 0) {
            TopicoAyuda topicoAyuda = topicoAyudaDAO.findOne(ticket.getIdTopicoAyuda().getIdTopicoAyuda());
            ticket.setIdTopicoAyuda(topicoAyuda);
        }
        
        if (ticket.getIdNivelAtencion().getIdNivelAtencion() > 0) {
            NivelAtencion nivelAtencion = nivelAtencionDAO.findOne(ticket.getIdNivelAtencion().getIdNivelAtencion());
            ticket.setIdNivelAtencion(nivelAtencion);
        }
        
        //ticketDAO.save(ticket);
        //Ocupo mi propia opcion de guardar del JpaController, en vez del guardado por defecto del DAO
        GestorJpaController jpa = new GestorJpaController();
        jpa.getTicketJC().crearTicket(
                ticket.getIdUsuario(),
                ticket.getIdDepto(),
                ticket.getIdTopicoAyuda(),
                ticket.getIdPersonalAtencion(),
                ticket.getIdNivelAtencion(),
                ticket.getIdEstado(),
                ticket.getIdCausaCierreTicket(),
                ticket.getDescripcion());

        return "redirect:/listarTickets";                              
    }
    
    @RequestMapping(value = "modificarTicket", method = RequestMethod.POST)
    public ModelAndView modificarTicket(@ModelAttribute Ticket ticket, BindingResult result, Model model) {
        ModelAndView mav = new ModelAndView("modificarTicket");
        
        Ticket ticketSeleccionado = ticketDAO.findOne(ticket.getIdTicket());
        mav.addObject("ticket", ticketSeleccionado);
        mav.addObject("causaCierre", causaCierreDAO.findAll());
        return mav;
    }
        
    @RequestMapping(value = "guardarModificacionTicket", method = RequestMethod.POST)
    public String guardarModificacionTicket(@ModelAttribute Ticket ticket, BindingResult result, Model model) {
        GestorJpaController jpa = new GestorJpaController();
        jpa.getTicketJC().modificarTicket(
                ticket.getIdTicket(),
                null,
                null,
                null,
                null,
                null,
                null,
                ticket.getDescripcion(),
                ticket.getCalificacion());

        return "redirect:/listarTickets";                              
    }
    
    @RequestMapping("listarTicketsNoResueltos")
    public ModelAndView listarTicketsNoResueltos() {
        ModelAndView mav = new ModelAndView("ticketsNoResueltos");
        GestorJpaController jpa = new GestorJpaController();
        mav.addObject("ticket", jpa.getTicketJC().getTicketsNoResueltos());
        return mav;
    }
    
    @RequestMapping("listarTicketsPorFecha")
    public ModelAndView listarTicketsPorFecha() {
        ModelAndView mav = new ModelAndView("ticketsPorFecha");
        GestorJpaController jpa = new GestorJpaController();
        mav.addObject("ticket", jpa.getTicketJC().getTicketsPorFecha());
        return mav;
    }
    
    /* Reportes de Tickets */
    
    @RequestMapping("verSolicitudesPorDepto")
    public ModelAndView verSolicitudesPorDepto() {
        ModelAndView mav = new ModelAndView("repSolicitudesPorDepto");
        mav.addObject("resultado", vSolicitudesTicketsDAO.findAll());
        return mav;
    }
    
    @RequestMapping("verTiempoPromedioResolucion")
    public ModelAndView verTiempoPromedioResolucion() {
        ModelAndView mav = new ModelAndView("repTiempoPromedioResolucion");
        mav.addObject("resultado", vResolucionTicketsDAO.findAll());
        return mav;
    }
}
