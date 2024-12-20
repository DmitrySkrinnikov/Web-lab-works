package com.example.weblabworks;

/**
 * Class for using at servlet ProjectList
 */
public class Project {
    private int id;
    private String project;
    private String customer;
    private String done;

    /**
     * Class constructor
     * @param id
     * @param project
     * @param customer
     * @param done
     */
    public Project(int id, String project, String customer, String done) {
        this.id = id;
        this.project = project;
        this.customer = customer;
        this.done = done;
    }

    /**
     * Getter Id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Getter Project name
     * @return
     */
    public String getProject() {
        return project;
    }

    /**
     * Getter Customer name
     * @return
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * Getter Done
     * @return
     */
    public String getDone() {
        return done;
    }

    /**
     * Setter Id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter Project name
     * @param project
     */
    public void setProject(String project) {
        this.project = project;
    }

    /**
     * Setter Customer name
     * @param customer
     */
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /**
     * Setter Done
     * @param done
     */
    public void setDone(String done) {
        this.done = done;
    }
}
