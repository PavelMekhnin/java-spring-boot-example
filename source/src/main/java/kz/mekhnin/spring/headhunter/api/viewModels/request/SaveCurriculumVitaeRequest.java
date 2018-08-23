package kz.mekhnin.spring.headhunter.api.viewModels.request;

public class SaveCurriculumVitaeRequest{
        private Long id;

        private String title;

        private String summary;

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
}
