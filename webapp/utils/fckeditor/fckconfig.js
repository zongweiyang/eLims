/*
 * FCKeditor - The text editor for Internet - http://www.fckeditor.net
 * Copyright (C) 2003-2008 Frederico Caldeira Knabben
 *
 * == BEGIN LICENSE ==
 *
 * Licensed under the terms of any of the following licenses at your
 * choice:
 *
 *  - GNU General Public License Version 2 or later (the "GPL")
 *    http://www.gnu.org/licenses/gpl.html
 *
 *  - GNU Lesser General Public License Version 2.1 or later (the "LGPL")
 *    http://www.gnu.org/licenses/lgpl.html
 *
 *  - Mozilla Public License Version 1.1 or later (the "MPL")
 *    http://www.mozilla.org/MPL/MPL-1.1.html
 *
 * == END LICENSE ==
 *
 * Editor configuration settings.
 *
 * Follow this link for more information:
 * http://docs.fckeditor.net/FCKeditor_2.x/Developers_Guide/Configuration/Configuration_Options
 */

FCKConfig.CustomConfigurationsPath = '' ;

FCKConfig.EditorAreaCSS = FCKConfig.BasePath + 'css/fck_editorarea.css' ;
FCKConfig.EditorAreaStyles = '' ;
FCKConfig.ToolbarComboPreviewCSS = '' ;

FCKConfig.DocType = '' ;

FCKConfig.BaseHref = '' ;

FCKConfig.FullPage = false ;

// The following option determines whether the "Show Blocks" feature is enabled or not at startup.
FCKConfig.StartupShowBlocks = false ;

FCKConfig.Debug = false ;
FCKConfig.AllowQueryStringDebug = true ;

FCKConfig.SkinPath = FCKConfig.BasePath + 'skins/silver/' ;
FCKConfig.SkinEditorCSS = '' ;	// FCKConfig.SkinPath + "|<minified css>" ;
FCKConfig.SkinDialogCSS = '' ;	// FCKConfig.SkinPath + "|<minified css>" ;

FCKConfig.PreloadImages = [ FCKConfig.SkinPath + 'images/toolbar.start.gif', FCKConfig.SkinPath + 'images/toolbar.buttonarrow.gif' ] ;

FCKConfig.PluginsPath = FCKConfig.BasePath + 'plugins/' ;

// FCKConfig.Plugins.Add( 'autogrow' ) ;
// FCKConfig.Plugins.Add( 'dragresizetable' );
FCKConfig.AutoGrowMax = 400 ;

// FCKConfig.ProtectedSource.Add( /<%[\s\S]*?%>/g ) ;	// ASP style server side code <%...%>
// FCKConfig.ProtectedSource.Add( /<\?[\s\S]*?\?>/g ) ;	// PHP style server side code
// FCKConfig.ProtectedSource.Add( /(<asp:[^\>]+>[\s|\S]*?<\/asp:[^\>]+>)|(<asp:[^\>]+\/>)/gi ) ;	// ASP.Net style tags <asp:control>

FCKConfig.AutoDetectLanguage	= true ;
FCKConfig.DefaultLanguage		= 'zh-cn' ;
FCKConfig.ContentLangDirection	= 'ltr' ;

FCKConfig.ProcessHTMLEntities	= true ;
FCKConfig.IncludeLatinEntities	= true ;
FCKConfig.IncludeGreekEntities	= true ;

FCKConfig.ProcessNumericEntities = false ;

FCKConfig.AdditionalNumericEntities = ''  ;		// Single Quote: "'"

FCKConfig.FillEmptyBlocks	= true ;

FCKConfig.FormatSource		= true ;
FCKConfig.FormatOutput		= true ;
FCKConfig.FormatIndentator	= '    ' ;

FCKConfig.EMailProtection = 'encode' ; // none | encode | function
FCKConfig.EMailProtectionFunction = 'mt(NAME,DOMAIN,SUBJECT,BODY)' ;

FCKConfig.StartupFocus	= false ;
FCKConfig.ForcePasteAsPlainText	= false ;
FCKConfig.AutoDetectPasteFromWord = true ;	// IE only.
FCKConfig.ShowDropDialog = true ;
FCKConfig.ForceSimpleAmpersand	= false ;
FCKConfig.TabSpaces		= 0 ;
FCKConfig.ShowBorders	= true ;
FCKConfig.SourcePopup	= false ;
FCKConfig.ToolbarStartExpanded	= true ;
FCKConfig.ToolbarCanCollapse	= true ;
FCKConfig.IgnoreEmptyParagraphValue = true ;
FCKConfig.FloatingPanelsZIndex = 10000 ;
FCKConfig.HtmlEncodeOutput = false ;

FCKConfig.TemplateReplaceAll = true ;
FCKConfig.TemplateReplaceCheckbox = true ;

FCKConfig.ToolbarLocation = 'In' ;
/**修改编辑器工具栏
FCKConfig.ToolbarSets["Default"] = [
		['Source','DocProps','-','Save','NewPage','Preview','-','Templates'],
		['Cut','Copy','Paste','PasteText','PasteWord','-','Print','SpellCheck'],
		['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
		['Form','Checkbox','Radio','TextField','Textarea','Select','Button','ImageButton','HiddenField'],
		'/',
		['Bold','Italic','Underline','StrikeThrough','-','Subscript','Superscript'],
		['OrderedList','UnorderedList','-','Outdent','Indent','Blockquote','CreateDiv'],
		['JustifyLeft','JustifyCenter','JustifyRight','JustifyFull'],
		['Link','Unlink','Anchor'],
		['Image','Flash','Table','Rule','Smiley','SpecialChar','PageBreak'],
		'/',
		['Style','FontFormat','FontName','FontSize'],
		['TextColor','BGColor'],
		['FitWindow','ShowBlocks','-','About']		// No comma for the last row.
	] ;
*/
FCKConfig.ToolbarSets["Default"] = [
		['Save','Preview','-','Templates'],
		['Cut','Copy','Paste','PasteText','PasteWord','-','Print','SpellCheck'],
		['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
		'/',
		['Bold','Italic','Underline','StrikeThrough','-','Subscript','Superscript'],
		['OrderedList','UnorderedList','-','Outdent','Indent','Blockquote','CreateDiv'],
		['JustifyLeft','JustifyCenter','JustifyRight','JustifyFull'],
		['Link','Unlink','Anchor'],
		['Image','Flash','Table','Rule','Smiley','SpecialChar','PageBreak'],
		'/',
		['FontFormat','FontName','FontSize'],
		['TextColor','BGColor'],
		['FitWindow','ShowBlocks','-']		// No comma for the last row.
	] ;
/*	
FCKConfig.ToolbarSets["bbs"] = [
	['Save','NewPage','Preview','-','Cut','Copy','Paste','PasteText','-','Find','Replace','-','Undo','Redo','-','SelectAll','-','Link','-','Image','Table','Rule','SpecialChar','Smiley'] , 
['Bold','Italic','Underline','-','JustifyLeft','JustifyCenter','JustifyRight','-','Form','Checkbox','Radio','Textarea','Select','Button','-','TextColor'] ] ;
 */
 FCKConfig.ToolbarSets["huifu"] = [
	['Save','NewPage','Preview','-','Cut','Copy','Paste','PasteText','-','Find','Replace','-','Undo','Redo','-','SelectAll','-','Link','-','Image','Table','Rule','SpecialChar','Smiley'] , 
['Bold','Italic','Underline','-','JustifyLeft','JustifyCenter','JustifyRight','-','Form','Checkbox','Radio','Textarea','Select','Button','-','TextColor'] ] ;
 /* 回帖 */
FCKConfig.ToolbarSets["bbs"] = [
['Smiley','Bold','Italic','FontFormat','FontName','FontSize','Image','OrderedList','UnorderedList','-','Link','Unlink'],
		['TextColor','BGColor'],
		['FitWindow','ShowBlocks','JustifyLeft','JustifyCenter','JustifyFull']
] ;
/*
  工具按钮设置
  EditSource 显示HTML源代码 StrikeThrough 删除线 
  Save 保存 Subscript 下标 
  NewPage 新建空白页面 Superscript 上标 
  Preview 预览 JustifyLeft 左对齐 
  Cut 剪切 JustifyCenter 居中对齐 
  Copy 复制 JustifyRight 右对齐 
  Paste 粘贴 JustifyFull 两端对齐 
  PasteText 纯文本粘贴 InsertOrderedList 自动编号 
  PasteWord 来自Word的粘贴 InsertUnorderedList 项目符号 
  Print 打印 Outdent 减少缩进 
  SpellCheck 拼写检查 Indent 增加缩进 
  Find 查找 ShowTableBorders 显示表格线 
  Replace 替换 ShowDetails 显示明细 
  Undo 撤销 Form 添加Form动作 
  Redo 还原 Checkbox 复选框 
  SelectAll 全选 Radio 单选按钮 
  RemoveFormat 去除格式 Input 单行文本框 
  Link 插入/编辑 链接 Textarea 滚动文本框 
  RemoveLink 去除连接 Select 下拉菜单 
  Anchor 锚点 Button 按钮 
  Image 插入/编辑 图片 ImageButton 图片按钮 
  Table 插入/编辑 表格 Hidden 隐藏 
  Rule 插入水平线 Zoom 显示比例 
  SpecialChar 插入特殊字符 FontStyleAdv 系统字体 
  UniversalKey 软键盘 FontStyle 字体样式 
  Smiley 插入表情符号 FontFormat 字体格式 
  About 关于 Font 字体 
  Bold 粗体 FontSize 字体大小 
  Italic 斜体 TextColor 文字颜色 
  Underline 下划线 BGColor 背景色 


*/
FCKConfig.EnterMode ='br' ;	// p | div | br
FCKConfig.ShiftEnterMode =  'p' ;			// p | div | br

FCKConfig.Keystrokes = [
	[ CTRL + 65 /*A*/, true ],
	[ CTRL + 67 /*C*/, true ],
	[ CTRL + 70 /*F*/, true ],
	[ CTRL + 83 /*S*/, true ],
	[ CTRL + 84 /*T*/, true ],
	[ CTRL + 88 /*X*/, true ],
	[ CTRL + 86 /*V*/, 'Paste' ],
	[ CTRL + 45 /*INS*/, true ],
	[ SHIFT + 45 /*INS*/, 'Paste' ],
	[ CTRL + 88 /*X*/, 'Cut' ],
	[ SHIFT + 46 /*DEL*/, 'Cut' ],
	[ CTRL + 90 /*Z*/, 'Undo' ],
	[ CTRL + 89 /*Y*/, 'Redo' ],
	[ CTRL + SHIFT + 90 /*Z*/, 'Redo' ],
	[ CTRL + 76 /*L*/, 'Link' ],
	[ CTRL + 66 /*B*/, 'Bold' ],
	[ CTRL + 73 /*I*/, 'Italic' ],
	[ CTRL + 85 /*U*/, 'Underline' ],
	[ CTRL + SHIFT + 83 /*S*/, 'Save' ],
	[ CTRL + ALT + 13 /*ENTER*/, 'FitWindow' ],
	[ SHIFT + 32 /*SPACE*/, 'Nbsp' ]
] ;

FCKConfig.ContextMenu = ['Generic','Link','Anchor','Image','Flash','Select','Textarea','Checkbox','Radio','TextField','HiddenField','ImageButton','Button','BulletedList','NumberedList','Table','Form','DivContainer'] ;
FCKConfig.BrowserContextMenuOnCtrl = false ;
FCKConfig.BrowserContextMenu = false ;

FCKConfig.EnableMoreFontColors = true ;
FCKConfig.FontColors = '000000,993300,333300,003300,003366,000080,333399,333333,800000,FF6600,808000,808080,008080,0000FF,666699,808080,FF0000,FF9900,99CC00,339966,33CCCC,3366FF,800080,999999,FF00FF,FFCC00,FFFF00,00FF00,00FFFF,00CCFF,993366,C0C0C0,FF99CC,FFCC99,FFFF99,CCFFCC,CCFFFF,99CCFF,CC99FF,FFFFFF' ;

FCKConfig.FontFormats	= 'p;h1;h2;h3;h4;h5;h6;pre;address;div' ;
FCKConfig.FontNames		= '宋体;黑体;隶书;楷体_GB2312;Arial;Comic Sans MS;Courier New;Tahoma;Times New Roman;Verdana' ;
FCKConfig.FontSizes		= 'smaller;larger;xx-small;x-small;small;medium;large;x-large;xx-large' ;

FCKConfig.StylesXmlPath		= FCKConfig.EditorPath + 'fckstyles.xml' ;
FCKConfig.TemplatesXmlPath	= FCKConfig.EditorPath + 'fcktemplates.xml' ;

FCKConfig.SpellChecker			= 'ieSpell' ;	// 'ieSpell' | 'SpellerPages'
FCKConfig.IeSpellDownloadUrl	= 'http://www.iespell.com/download.php' ;
FCKConfig.SpellerPagesServerScript = 'server-scripts/spellchecker.php' ;	// Available extension: .php .cfm .pl
FCKConfig.FirefoxSpellChecker	= false ;

FCKConfig.MaxUndoLevels = 15 ;

FCKConfig.DisableObjectResizing = false ;
FCKConfig.DisableFFTableHandles = true ;

FCKConfig.LinkDlgHideTarget		= false ;
FCKConfig.LinkDlgHideAdvanced	= false ;

FCKConfig.ImageDlgHideLink		= false ;
FCKConfig.ImageDlgHideAdvanced	= false ;

FCKConfig.FlashDlgHideAdvanced	= false ;

FCKConfig.ProtectedTags = '' ;

// This will be applied to the body element of the editor
FCKConfig.BodyId = '' ;
FCKConfig.BodyClass = '' ;

FCKConfig.DefaultStyleLabel = '' ;
FCKConfig.DefaultFontFormatLabel = '' ;
FCKConfig.DefaultFontLabel = '' ;
FCKConfig.DefaultFontSizeLabel = '' ;

FCKConfig.DefaultLinkTarget = '' ;

// The option switches between trying to keep the html structure or do the changes so the content looks like it was in Word
FCKConfig.CleanWordKeepsStructure = false ;

// Only inline elements are valid.
FCKConfig.RemoveFormatTags = 'b,big,code,del,dfn,em,font,i,ins,kbd,q,samp,small,span,strike,strong,sub,sup,tt,u,var' ;

// Attributes that will be removed
FCKConfig.RemoveAttributes = 'class,style,lang,width,height,align,hspace,valign' ;

FCKConfig.CustomStyles =
{
	'Red Title'	: { Element : 'h3', Styles : { 'color' : 'Red' } }
};

// Do not add, rename or remove styles here. Only apply definition changes.
FCKConfig.CoreStyles =
{
	// Basic Inline Styles.
	'Bold'			: { Element : 'strong', Overrides : 'b' },
	'Italic'		: { Element : 'em', Overrides : 'i' },
	'Underline'		: { Element : 'u' },
	'StrikeThrough'	: { Element : 'strike' },
	'Subscript'		: { Element : 'sub' },
	'Superscript'	: { Element : 'sup' },

	// Basic Block Styles (Font Format Combo).
	'p'				: { Element : 'p' },
	'div'			: { Element : 'div' },
	'pre'			: { Element : 'pre' },
	'address'		: { Element : 'address' },
	'h1'			: { Element : 'h1' },
	'h2'			: { Element : 'h2' },
	'h3'			: { Element : 'h3' },
	'h4'			: { Element : 'h4' },
	'h5'			: { Element : 'h5' },
	'h6'			: { Element : 'h6' },

	// Other formatting features.
	'FontFace' :
	{
		Element		: 'span',
		Styles		: { 'font-family' : '#("Font")' },
		Overrides	: [ { Element : 'font', Attributes : { 'face' : null } } ]
	},

	'Size' :
	{
		Element		: 'span',
		Styles		: { 'font-size' : '#("Size","fontSize")' },
		Overrides	: [ { Element : 'font', Attributes : { 'size' : null } } ]
	},

	'Color' :
	{
		Element		: 'span',
		Styles		: { 'color' : '#("Color","color")' },
		Overrides	: [ { Element : 'font', Attributes : { 'color' : null } } ]
	},

	'BackColor'		: { Element : 'span', Styles : { 'background-color' : '#("Color","color")' } },

	'SelectionHighlight' : { Element : 'span', Styles : { 'background-color' : 'navy', 'color' : 'white' } }
};

// The distance of an indentation step.
FCKConfig.IndentLength = 40 ;
FCKConfig.IndentUnit = 'px' ;

// Alternatively, FCKeditor allows the use of CSS classes for block indentation.
// This overrides the IndentLength/IndentUnit settings.
FCKConfig.IndentClasses = [] ;

// [ Left, Center, Right, Justified ]
FCKConfig.JustifyClasses = [] ;

// The following value defines which File Browser connector and Quick Upload
// "uploader" to use. It is valid for the default implementaion and it is here
// just to make this configuration file cleaner.
// It is not possible to change this value using an external file or even
// inline when creating the editor instance. In that cases you must set the
// values of LinkBrowserURL, ImageBrowserURL and so on.
// Custom implementations should just ignore it.
var _FileBrowserLanguage	= 'php' ;	// asp | aspx | cfm | lasso | perl | php | py
var _QuickUploadLanguage	= 'php' ;	// asp | aspx | cfm | lasso | perl | php | py

// Don't care about the following two lines. It just calculates the correct connector
// extension to use for the default File Browser (Perl uses "cgi").
var _FileBrowserExtension = _FileBrowserLanguage == 'perl' ? 'cgi' : _FileBrowserLanguage ;
var _QuickUploadExtension = _QuickUploadLanguage == 'perl' ? 'cgi' : _QuickUploadLanguage ;

FCKConfig.LinkBrowser = true ;
FCKConfig.LinkBrowserURL = FCKConfig.BasePath + 'filemanager/browser/default/browser.html?Connector=' + encodeURIComponent( FCKConfig.BasePath + 'filemanager/connectors/' + _FileBrowserLanguage + '/connector.' + _FileBrowserExtension ) ;
FCKConfig.LinkBrowserWindowWidth	= FCKConfig.ScreenWidth * 0.7 ;		// 70%
FCKConfig.LinkBrowserWindowHeight	= FCKConfig.ScreenHeight * 0.7 ;	// 70%

FCKConfig.ImageBrowser = true ;
FCKConfig.ImageBrowserURL = FCKConfig.BasePath + 'filemanager/browser/default/browser.html?Type=Image&Connector=' + encodeURIComponent( FCKConfig.BasePath + 'filemanager/connectors/' + _FileBrowserLanguage + '/connector.' + _FileBrowserExtension ) ;
FCKConfig.ImageBrowserWindowWidth  = FCKConfig.ScreenWidth * 0.7 ;	// 70% ;
FCKConfig.ImageBrowserWindowHeight = FCKConfig.ScreenHeight * 0.7 ;	// 70% ;

FCKConfig.FlashBrowser = true ;
FCKConfig.FlashBrowserURL = FCKConfig.BasePath + 'filemanager/browser/default/browser.html?Type=Flash&Connector=' + encodeURIComponent( FCKConfig.BasePath + 'filemanager/connectors/' + _FileBrowserLanguage + '/connector.' + _FileBrowserExtension ) ;
FCKConfig.FlashBrowserWindowWidth  = FCKConfig.ScreenWidth * 0.7 ;	//70% ;
FCKConfig.FlashBrowserWindowHeight = FCKConfig.ScreenHeight * 0.7 ;	//70% ;

FCKConfig.LinkUpload = true ;
FCKConfig.LinkUploadURL = FCKConfig.BasePath + 'filemanager/connectors/' + _QuickUploadLanguage + '/upload.' + _QuickUploadExtension ;
FCKConfig.LinkUploadAllowedExtensions	= ".(7z|aiff|asf|avi|bmp|csv|doc|fla|flv|gif|gz|gzip|jpeg|jpg|mid|mov|mp3|mp4|mpc|mpeg|mpg|ods|odt|pdf|png|ppt|pxd|qt|ram|rar|rm|rmi|rmvb|rtf|sdc|sitd|swf|sxc|sxw|tar|tgz|tif|tiff|txt|vsd|wav|wma|wmv|xls|xml|zip)$" ;			// empty for all
FCKConfig.LinkUploadDeniedExtensions	= "" ;	// empty for no one

FCKConfig.ImageUpload = true ;
FCKConfig.ImageUploadURL = FCKConfig.BasePath + 'filemanager/connectors/' + _QuickUploadLanguage + '/upload.' + _QuickUploadExtension + '?Type=Image' ;
FCKConfig.ImageUploadAllowedExtensions	= ".(jpg|gif|jpeg|png|bmp)$" ;		// empty for all
FCKConfig.ImageUploadDeniedExtensions	= "" ;							// empty for no one

FCKConfig.FlashUpload = true ;
FCKConfig.FlashUploadURL = FCKConfig.BasePath + 'filemanager/connectors/' + _QuickUploadLanguage + '/upload.' + _QuickUploadExtension + '?Type=Flash' ;
FCKConfig.FlashUploadAllowedExtensions	= ".(swf|flv)$" ;		// empty for all
FCKConfig.FlashUploadDeniedExtensions	= "" ;					// empty for no one

FCKConfig.SmileyPath	= FCKConfig.BasePath + 'images/smiley/msn/' ;
FCKConfig.SmileyImages	= ['regular_smile.gif','sad_smile.gif','wink_smile.gif','teeth_smile.gif','confused_smile.gif','tounge_smile.gif','embaressed_smile.gif','omg_smile.gif','whatchutalkingabout_smile.gif','angry_smile.gif','angel_smile.gif','shades_smile.gif','devil_smile.gif','cry_smile.gif','lightbulb.gif','thumbs_down.gif','thumbs_up.gif','heart.gif','broken_heart.gif','kiss.gif','envelope.gif'] ;
FCKConfig.SmileyColumns = 8 ;
FCKConfig.SmileyWindowWidth		= 320 ;
FCKConfig.SmileyWindowHeight	= 210 ;

FCKConfig.BackgroundBlockerColor = '#ffffff' ;
FCKConfig.BackgroundBlockerOpacity = 0.50 ;

FCKConfig.MsWebBrowserControlCompat = false ;

FCKConfig.PreventSubmitHandler = false ;
