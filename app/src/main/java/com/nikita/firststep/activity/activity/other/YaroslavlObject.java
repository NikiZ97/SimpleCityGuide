package com.nikita.firststep.activity.activity.other;

/**
 * Created by nikita on 12.11.16.
 * Model class of single item of CardView with RecyclerView
 * Defines single item of shops list
 */
public class YaroslavlObject implements Comparable {
    private String name;
    private double distance;
    private int image;
    private double latitude;
    private double longitude;
    private String timeToGo;
    private int tabs;
    private int theme;
    private String description;
    private String address;
    private String email;
    private String openTo;
    private String phone;
    private int fab;
    private int fab1;
    private int fab2;
    private int fab3;
    private int fab4;
    private int markCount;
    private String category;
    private String location;


    private YaroslavlObject() {
    }

    public String getAddress() {return address;}

    public String getCategory() {return category;}

    public String getDescription() {return description;}

    public double getDistance() {return distance;}

    public String getEmail() {return email;}

    public int getFab1() {return fab1;}

    public int getFab2() {return fab2;}

    public int getFab3() {return fab3;}

    public int getFab() {return fab;}

    public int getImage() {return image;}

    public double getLatitude() {return latitude;}

    public String getLocation() {return location;}

    public double getLongitude() {return longitude;}

    public int getMarkCount() {return markCount;}

    public String getName() {return name;}

    public String getOpenTo() {return openTo;}

    public String getPhone() {return phone;}

    public int getTabs() {return tabs;}

    public int getTheme() {return theme;}

    public String getTimeToGo() {return timeToGo;}

    public int getFab4() {return fab4;}

    public static Builder newBuilder() {
        return  new YaroslavlObject().new Builder();
    }

    @Override
    public int compareTo(Object o) {
        if (((YaroslavlObject)o).getDistance() > distance) {
            return -1;
        } if (((YaroslavlObject)o).getDistance() == distance) {
            return 0;
        } else
            return 1;
    }

    public class Builder {

        private Builder() {}

        public Builder setAddress(String address) {
            YaroslavlObject.this.address = address;
            return this;
        }

        public Builder setCategory(String category) {
            YaroslavlObject.this.category = category;
            return this;
        }

        public Builder setDescription(String description) {
            YaroslavlObject.this.description = description;
            return this;
        }

        public Builder setDistance(double distance) {
            YaroslavlObject.this.distance = distance;
            return this;
        }

        public Builder setEmail(String email) {
            YaroslavlObject.this.email = email;
            return this;
        }

        public Builder setFab1(int fab1) {
            YaroslavlObject.this.fab1 = fab1;
            return this;
        }

        public Builder setFab2(int fab2) {
            YaroslavlObject.this.fab2 = fab2;
            return this;
        }

        public Builder setFab3(int fab3) {
            YaroslavlObject.this.fab3 = fab3;
            return this;
        }

        public Builder setFab(int fab) {
            YaroslavlObject.this.fab = fab;
            return this;
        }

        public Builder setImage(int image) {
            YaroslavlObject.this.image = image;
            return this;
        }

        public Builder setLatitude(double latitude) {
            YaroslavlObject.this.latitude = latitude;
            return this;
        }

        public Builder setLocation(String location) {
            YaroslavlObject.this.location = location;
            return this;
        }

        public Builder setLongitude(double longitude) {
            YaroslavlObject.this.longitude = longitude;
            return this;
        }

        public Builder setMarkCount(int count) {
            YaroslavlObject.this.markCount = count;
            return this;
        }

        public Builder setName(String name) {
            YaroslavlObject.this.name = name;
            return this;
        }

        public Builder setOpenTo(String openTo) {
            YaroslavlObject.this.openTo = openTo;
            return this;
        }

        public Builder setPhone(String phone) {
            YaroslavlObject.this.phone = phone;
            return this;
        }

        public Builder setTabs(int tabs) {
            YaroslavlObject.this.tabs = tabs;
            return this;
        }

        public Builder setTheme(int theme) {
            YaroslavlObject.this.theme = theme;
            return this;
        }

        public Builder setTimeToGo(String timeToGo) {
            YaroslavlObject.this.timeToGo = timeToGo;
            return this;
        }

        public Builder setFab4(int fab4) {
            YaroslavlObject.this.fab4 = fab4;
            return this;
        }

        public YaroslavlObject build() {
            return YaroslavlObject.this;
        }
    }
}
