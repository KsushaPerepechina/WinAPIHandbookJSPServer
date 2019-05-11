package by.bsuir.winapihandbookjspserver.bean;

public class WinAPIFunction {
    private Integer id = null;
    private String name = "";
    private String params = "";
    private String returnValue = "";
    private String description = "";

    public WinAPIFunction() {
    }

    public WinAPIFunction(String name, String params, String returnValue, String description) {
        this.name = name;
        this.params = params;
        this.returnValue = returnValue;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String rDescription) {
        this.description = rDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WinAPIFunction that = (WinAPIFunction) o;
        return (getName() != null ? getName().equals(that.getName()) : that.getName() == null)
                && (getParams() != null ? getParams().equals(that.getParams()) : that.getParams() == null)
                && (getReturnValue() != null ? getReturnValue().equals(that.getReturnValue()) : that.getReturnValue() == null)
                && (getDescription() != null ? getDescription().equals(that.getDescription()) : that.getDescription() == null);
    }
}
