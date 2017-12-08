package com.rjt.projectmanagementsystem.model;

import java.util.List;

/**
 * Created by Yifu on 12/3/2017.
 */

public class Projects {
    private List<ProjectsBean> Projects;

    public List<ProjectsBean> getProjects() {
        return Projects;
    }

    public void setProjects(List<ProjectsBean> Projects) {
        this.Projects = Projects;
    }

    public static class ProjectsBean {
        /**
         * id : 1
         * projectname : PMS
         * projectstatus : completed
         * assignto : aa@aa.com
         * projectdesc : project management system
         * startdate : 0000-00-00 00:00:00
         * endstart : 0000-00-00 00:00:00
         */

        private String id;
        private String projectname;
        private String projectstatus;
        private String assignto;
        private String projectdesc;
        private String startdate;
        private String endstart;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProjectname() {
            return projectname;
        }

        public void setProjectname(String projectname) {
            this.projectname = projectname;
        }

        public String getProjectstatus() {
            return projectstatus;
        }

        public void setProjectstatus(String projectstatus) {
            this.projectstatus = projectstatus;
        }

        public String getAssignto() {
            return assignto;
        }

        public void setAssignto(String assignto) {
            this.assignto = assignto;
        }

        public String getProjectdesc() {
            return projectdesc;
        }

        public void setProjectdesc(String projectdesc) {
            this.projectdesc = projectdesc;
        }

        public String getStartdate() {
            return startdate;
        }

        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }

        public String getEndstart() {
            return endstart;
        }

        public void setEndstart(String endstart) {
            this.endstart = endstart;
        }
    }
}
