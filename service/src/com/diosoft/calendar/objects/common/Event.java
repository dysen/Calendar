package com.diosoft.calendar.objects.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by dysen on 1/12/15.
 * title : String
 * description : String
 * email : String
 * id : UUID
 * attenders: List<String>
 * startDate: Date
 * endDate:Date
 */
public class Event implements Serializable {
    private final String title;
    private final String description;
    private final String email;
    private final UUID id;
    private final List<Person> attenders;
    private final Date startDate;
    private final Date endDate;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public UUID getId() {
        return id;
    }

    public List<Person> getAttenders() {
        return attenders;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (attenders != null ? !attenders.equals(event.attenders) : event.attenders != null) return false;
        if (description != null ? !description.equals(event.description) : event.description != null) return false;
        if (email != null ? !email.equals(event.email) : event.email != null) return false;
        if (endDate != null ? !endDate.equals(event.endDate) : event.endDate != null) return false;
        if (id != null ? !id.equals(event.id) : event.id != null) return false;
        if (startDate != null ? !startDate.equals(event.startDate) : event.startDate != null) return false;
        if (title != null ? !title.equals(event.title) : event.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (attenders != null ? attenders.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", attenders=" + attenders +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    public Event(Builder builder) {
        title = builder.title;

        description = builder.description;
        email = builder.email;
        id = builder.id;
        attenders = builder.attenders;
        startDate = builder.startDate;
        endDate = builder.endDate;
    }

    public static class Builder {
        private String title;
        private String description;
        private String email;
        private UUID id;
        private List<Person> attenders;
        private Date startDate;
        private Date endDate;
//
//        public String getTitle() {
//            return title;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//        public String getEmail() {
//            return email;
//        }
//
//        public UUID getId() {
//            return id;
//        }
//
//        public List<Person> getAttenders() {
//            return attenders;
//        }
//
//        public Date getStartDate() {
//            return startDate;
//        }
//
//        public Date getEndDate() {
//            return endDate;
//        }

        public Builder title(String _title) {
            this.title = _title;
            return this;
        }

        public Builder description(String _description) {
            this.description = _description;
            return this;
        }

        public Builder email(String _email) {
            this.email = _email;
            return this;
        }

        public Builder id(UUID _id) {
            this.id = _id;
            return this;
        }

        public Builder attenders(List<Person> _attenders) {
            this.attenders = _attenders;
            return this;
        }

        public Builder startDate(Date _startDate) {
            this.startDate = _startDate;
            return this;
        }

        public Builder endDate(Date _endDate) {
            this.startDate = _endDate;
            return this;
        }

        public Event build() {
            return new Event(this);
        }


    }

}
