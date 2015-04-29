package cn.labsoft.labos.framework.common.sesseionutils;

/**
 * 
 * <strong>Title : SpringContext </strong>. <br>
 * <strong>Description : 向service层以外提供service业务接口的工厂</strong> <br>
 * <strong>Create on : Nov 13, 2009 11:41:35 AM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 *
 * @author <br>
 * @version <strong>rsLab v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public interface SessionConstants {
	
	public static final String MESSAGE_PROPERTIES_NAME = "ApplicationResources"; // struts message properties name


    /**
      * User static constants
      */
    public static final String ENTER_USER_KEY = "userId";
    public static final String ENTER_USER_PWD = "userPwd";
    public static final String ENTER_USER_STATUS = "userstatus";

    public static final int USER_STATUS_FRONT_END=0;
    public static final String USER_STATUS_FRONT_END_DESC="FRONT-END MANAGER USER";
    public static final int USER_STATUS_BACK_END=1;
    public static final String USER_STATUS_BACK_END_DESC="BACK-END MANAGER USER";
    public static final String ENTER_USERNAME_KEY = "userName";
    
    
	public static final int PAGE_COUNTPAGE = 6;    //table rows every page number
	public static final int PAGE_DEFAULT_CURPAGE = 1;  //default page number
	public static final int PAGE_DEFAULT_PARAMNUM = 10;  //default page parameter number
	public static final int PAGE_COUNTNUM = 1;  //used in before or next page count
	
	public static final String ALLIANCE_UPLOAD_PATH = "upload";  //upload picture's path
	
	public static final String ALLIANCE_MODELNAME_ADMIN = "admin";  //the admin model name
	public static final String ALLIANCE_MODELNAME_BLOG = "blog";  //the blog model name
	public static final String ALLIANCE_MODELNAME_ABLUM = "album";  //the album model name
	public static final String ALLIANCE_MODELNAME_MUSIC = "music";  //the music model name
	public static final String ALLIANCE_MODELNAME_VIDEO = "video";  //the video model name
	public static final String ALLIANCE_MODELNAME_SPACE = "space";  //the space model name
	public static final String ALLIANCE_MODELNAME_USER = "user";  //the user login and register model name
	
	
	/***************  front begin  *****************************************************/
	
	public static final long FRONT_TOTALNUMBER_DEFAULT = 1; //the number of click,it is used in all model
	public static final long FRONT_CLICKNUMBER_EFAULT = 0; //the number of click,it is used in all model
	
	/* active status (used in all model of front)*/
	public static final int FRONT_ISACTIVE_STATUS_ACTIVE = 1;//status is active(default value)
	public static final int FRONT_ISACTIVE_STATUS_FREEZE = 0;//status is not active
	
	/* a record's used status */
	public static final int FRONT_USERD_STATUS_RECYCLE = 1;// the status of record is recycle
	public static final int FRONT_USERD_STATUS_NORMAL = 0;//the status of record is normal��default value��
	public static final int FRONT_USERD_STATUS_DRAFT = 2;//the status of record is draft
	
	/* a picture/article/record is allowed to comment or not */
	public static final int FRONT_COMMENT_STATUS_ALLOW = 1;// the picture/article/record is allowed to comment��default value��
	public static final int FRONT_COMMENT_STATUS_REFUSE = 0;// the picture/article/record refuses to comment
	
	/* the open status of a record */
	public static final int FRONT_CATEGORY_OPEN_STATUS_PRIVATE = 0;// the record is private
	public static final int FRONT_CATEGORY_OPEN_STATUS_OPENALL = 1;//the record is openned to all users��default value��
	public static final int FRONT_CATEGORY_OPEN_STATUS_FRIENDS = 2;//the record is openned to all friends
	public static final int FRONT_CATEGORY_OPEN_STATUS_CLOSEDFRIENDS = 3;//the record is openned to close friends
	public static final int FRONT_CATEGORY_OPEN_STATUS_TRIBE = 4;//the record is openned to user's tribe
	
	/* a record's distillate status */
	public static final int FRONT_DISTILLATE_STATUS_NO = 0;// the record is not distillate(default value)
	public static final int FRONT_DISTILLATE_STATUS_YES = 1;//the record is distillate
	
	/* a record's top status */
	public static final int FRONT_TOP_STATUS_NO = 0;// the record is not top(default value)
	public static final int FRONT_TOP_STATUS_YES = 1;//the record is top
	
	/* a record is exterior or not  */
	public static final int FRONT_STYLE_DEFAULT = 1;// the record is not exterior(default value)
	public static final int FRONT_STYLE_NO_DEFAULT = 0;// the record is exterior

	/* style is default  or not  */
	public static final int PICTURE_EXTERIOR_STATUS_NO = 0;// the record is not exterior(default value)
	public static final int PICTURE_EXTERIOR_STATUS_YES = 1;// the record is exterior
	
	/***************  front end  *****************************************************/

	public static final String ERROR_SHOWINFO_NAME = "ERRORINFO";
	
	public static final String DEFAULT_SCHEMA = "CURRENTSCHEMA";
	
	public static final String TENANTID = "TENANTID";
	
}
