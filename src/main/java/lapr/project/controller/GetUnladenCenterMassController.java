package lapr.project.controller;

import lapr.project.domain.model.Company;

public class GetUnladenCenterMassController {
    /**
     * Company instance of the session.
     */
    private Company company;
    /**
     * Constructor for the controller.
     */
    public GetUnladenCenterMassController() {
        this(App.getInstance().getCompany());
    }
    /**
     * Constructor receiving the company as an argument.
     *
     * @param company instance of company to be used.
     */
    public GetUnladenCenterMassController(Company company) {
        this.company=company;
    }
}
