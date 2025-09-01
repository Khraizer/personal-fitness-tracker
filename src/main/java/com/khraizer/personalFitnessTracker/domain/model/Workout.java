package com.khraizer.personalFitnessTracker.domain.model;

public record Workout(Long id, String name, String description) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String description;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Workout build() {
            return new Workout(id, name, description);
        }
    }
}
