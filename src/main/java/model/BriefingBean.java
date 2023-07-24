package model;

public class BriefingBean {

    public BriefingBean() {};

    public BriefingBean(int userId, String productCode, String target, String style,
                        String goals, String note, String colorPalette) {
        this.userId = userId;
        this.productCode = productCode;
        this.target = target;
        this.style = style;
        this.goals = goals;
        this.note = note;
        this.colorPalette = colorPalette;
    }

    public BriefingBean(int id, int userId, String productCode, String target,
                        String style, String goals, String note, String colorPalette) {
        this.userId = userId;
        this.productCode = productCode;
        this.target = target;
        this.style = style;
        this.goals = goals;
        this.note = note;
        this.colorPalette = colorPalette;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setColorPalette(String colorPalette) {
        this.colorPalette = colorPalette;
    }

    public String getColorPalette() {
        return colorPalette;
    }

    public boolean checkBriefingValue(){
        boolean matchflag = true;

        if (this.getProductCode() == null || this.getProductCode().isEmpty())
            matchflag = false;
        else if (this.getTarget() == null || this.getTarget().isEmpty())
            matchflag = false;
        else if (this.getStyle() == null || this.getStyle().isEmpty())
            matchflag = false;
        else if (this.getGoals() == null || this.getGoals().isEmpty())
            matchflag = false;
        else if (this.getColorPalette() == null || this.getColorPalette().isEmpty())
            matchflag = false;
        else if (this.getNote() == null || this.getNote().isEmpty())
            matchflag = false;

        return matchflag;
    }

    private int id, userId;
    private String productCode, target, style, goals, note;
    private String colorPalette;
}
