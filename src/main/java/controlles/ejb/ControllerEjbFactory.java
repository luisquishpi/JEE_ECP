package controlles.ejb;

import controllers.ControllerFactory;
import controllers.EliminarTemaController;
import controllers.NuevoTemaController;
import controllers.ReportController;
import controllers.VotarController;

public class ControllerEjbFactory extends ControllerFactory {

    private VotarController votarController;

    private NuevoTemaController nuevoTemaController;

    private EliminarTemaController eliminarTemaController;

    private ReportController reportController;

    @Override
    public VotarController getVotarCotroller() {
        if (votarController == null)
            votarController = new VotarCotrollerEjb();
        return votarController;
    }

    @Override
    public NuevoTemaController getNuevoTemaController() {
        if (nuevoTemaController == null)
            nuevoTemaController = new NuevoTemaCotrollerEjb();
        return nuevoTemaController;
    }

    @Override
    public EliminarTemaController getEliminarTemaController() {
        if (eliminarTemaController == null)
            eliminarTemaController = new EliminarTemaCotrollerEjb();
        return eliminarTemaController;
    }

    @Override
    public ReportController getReportController() {
        if (reportController == null)
            reportController = new ReportCotrollerEjb();
        return reportController;
    }

}
