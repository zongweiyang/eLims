/******************************************************/
/* 文件名:CCITCertCtrl.js                             */
/* 功  能:                                    			  */
/* 作  者: 杨俊清                                  	  */
/* 日  期:2008-3-28                     						  */
/******************************************************/
document.write("<object classid=\"CLSID:936510B6-3A00-4616-9FD1-E77CAA733301\" id=\"CertControl\">");
document.write("</object>");

function dec2hex(decnum)
{
    var hexcode;
    var hexnum = "0123456789ABCDEF";
    var n;
    n = 0;
    hexcode = "";
    while (n < 16)
    {
    	i =decnum & 0x0000000F;
        hexcode = hexnum.substring(i, i + 1) + hexcode;
        decnum = decnum >> 4;
        n ++;
    }

    hexcode = "0x" + hexcode;
    return hexcode;
}

/*
功能：用于检测用户是否安装了联通iPASS客户端控件。
说明：由页面自动加载此方法。如果用户正常安装并注册了联通iPASS客户端控件，无提示；否则，输出警告框。
输入：无
输出：无
*/
function checkComSetuped()
{
   try
   {
      CertControl.test();
   }
   catch(ex)
   {
      alert("您没有安装联通iPASS客户端控件!");
   }
}

/*
功能：判断是否插上联通iPASS。
输入：无
返回值：插上：1
				未插上：0
*/
function isUKConnect()
{
	var ret = CertControl.ISUKConnect();
	return ret;
}

/*
功能：用于初始化联通iPASS。
输入：type ---- 表示非实名或者实名制证书用途，目前默认为0
返回值：成功：返回0
        失败：返回非0
*/
function init(type)
{
	var ret=1;
	var iret=isUKConnect();
	if(iret!=1)
	{
		alert("请确认iPASS网盾已正常连接！");
	}else
	{
		if (type == 0)
		{
			ret = CertControl.init("PROVIDE","","");
		}
		else
			{
				ret = CertControl.init("PROVIDE1","","");
			}
	}
	return ret;
}

/*
功能：取得数字证书。
输入：无
返回值：
				成功：数字证书
				失败：空串
注意：请在初始化函数后使用
*/
function getSignCert()
{
	var signCert = 0;
	signCert = CertControl.getSignCert();
	return signCert;
}
/*
功能：取得加密证书。只适用于实名制证书
输入：无
返回值：
				成功：加密证书
				失败：空串
注意：请在初始化函数后使用
*/
function getEncCert()
{
	var encCert = 0;
	encCert = CertControl.getExchangeCert();
	return encCert;
}
/*
功能：取得证书拥有者姓名。
输入：无
返回值：
				成功：证书拥有者姓名
				失败：空串
注意：请在初始化函数后使用
*/
function getUserName()
{
	var userName = 0;
	userName = CertControl.GetUserName();
	return userName;
}

/*
功能：对明文进行数字签名。
输入：plain：待签名的明文
返回值：成功：签名后的签名值
				失败：空串
注意：请在初始化函数后使用
*/
function signNature(plain)
{
	var ret=0;
	var algorithm = "SHA1WITHRSA";//签名算法标示
	ret = CertControl.signNature(plain,algorithm);
	return ret;
}
/*
功能：释放资源。
输入：无
返回值：无
*/
function end()
{
	CertControl.end();
}

/*
功能：取得数字证书信息，包括DN项和有效期。
输入：signcert-----证书，base64编码
返回值：成功：数字证书信息
				失败：空串
*/
function getCertInfo(signcert)
{
	var ret=0;
	ret = CertControl.getCertInfo(signcert);
	return ret;
}
/*
功能：取得数字证书扩展项信息。
输入：extendID------要取得的证书ID标识
      certData-----证书，base64编码
返回值：成功：证书扩展项信息
				失败：空串
*/
function getCertExtend(extendID,certData)
{
	var ret = 0;
	ret = CertControl.GetCertExtends(extendID,certData);
	return ret;
}

/*
功能：取得数字证书的序列号。
输入：signcert-----证书，base64编码
返回值：成功：数字证书序列号
				失败：空串
*/
function getCertSN(signcert)
{
	var ret = 0;
	ret = CertControl.GetCertSN(signcert);
  return ret;
}

/*
功能：取得数字证书的颁发者。
输入：signcert-----证书，base64编码
返回值：成功：数字证书颁发者信息
				失败：空串
*/
function getCertIssuer(signcert)
{
	var ret = 0;
	ret = CertControl.GetCertIssuer(signcert);
	return ret;
}

/*
功能：验证数字签名。
输入：plain：签名的明文，signData：签名值，signCert：用来验证签名的数字证书
返回值：验证签名结果：
					成功：0
					失败：非0
*/
function verifySign(plain,signData,signCert)
{
	var ret=1;
	ret = CertControl.verifySign(plain,signData,signCert,"SHA1WITHRSA");
	return ret;
}

/*
功能：取得指定长度的随机数。
输入：len：随机数的长度，长度在1-64之间
返回值：成功：随机数
				失败：空串
*/
function genRandNum(len)
{
	var ret=0;
	ret = CertControl.genRandNum(len);
	return ret;
}
/*
功能：取得设备序列号。
输入：无
返回值：成功:设备序列号
				失败：空串
*/
function readUKSN()
{
	var ret=0;
	ret = CertControl.readUKSN();
  return ret;
}
/*
功能：检查用户证书有效性。
输入：certData-----需要检查的证书，base64编码
返回值：成功:返回1
				失败：返回0
*/
function checkCert(certData)
{
	var ret = 0;
	ret = CertControl.checkCert(certData);
  return ret;
}
/*
功能：制作数字信封。
输入：types-----数字信封源信息类型，如果为0表示是对某一文件做数字信封，其他为对某一字节串做数字信封。
      srcData-----数字信封源，若types为0，srcData表示文件的绝对路径；若types为其他，srcData为需要做数字信封的源信息。
      certData-----接收者的数字证书，若为双证书需要为加密证书，base64编码
      outPath --- 数字信封的保存地址，全路径
返回值：成功:若为文件，返回“SUCCESS”，字符流返回数字信封值
				失败：返回“ERROR”
*/
function makeEnvlope(types, srcData, certData,outPath)
{
	var envData = 0;
	envData = CertControl.makeEnv(types, srcData, certData,outPath);
  return envData;
}

/*
功能：解数字信封。此接口针对字符流的解数字信封
输入：envData-----数字信封值，base64编码
返回值：成功:数字信封值
				失败：空串
*/
function openEnvlope(envData)
{
	var plain = 0;
	plain = CertControl.openEnv(envData);
  return plain;
}

/*
功能：解数字信封。此接口针对文件的解数字信封
输入：inFilePath-----数字信封的文件存放地址
      outPath ----- 明文的输出路径
返回值：成功:0
				失败：其他
*/
function openEnvlopeFile(inFilePath,outPath)
{
	var plain = 0;
	plain = CertControl.openEnvFile(inFilePath,outPath);
  return plain;
}

/**********************************************************/
/*隐藏区相关                                              */
/**********************************************************/
/*
*/
function writeFile(certData,fileData,fileType)
{
	var ret;
	try
	{
		ret = CertControl.writeFileFromHidden(certData,fileData,fileType);
   }
    catch(e)
    {
        if (e.description != "")
        {
           // alert("发生错误！\r\n错误码:" + dec2hex(e.number) + "\r\n" + "错误描述：" + e.description);
        }
        else
        {
           // alert("错误码:" + dec2hex(e.number));
        }
        ret = -1;
    }
   
  return ret;
}

function readFile(certData,fileType)
{
	var fileData = 0;
	try
	{
		fileData = CertControl.readFileFromHidden(certData,fileType);
   }
    catch(e)
    {
        if (e.description != "")
        {
           // alert("发生错误！\r\n错误码:" + dec2hex(e.number) + "\r\n" + "错误描述：" + e.description);
        }
        else
        {
            //alert("错误码:" + dec2hex(e.number));
        }
        fileData = "";
    }
  return fileData;
}

function deleteService(certData)
{
	var ret = 0;
	try
	{
		ret = CertControl.deleteService(certData);
   }
    catch(e)
    {
        if (e.description != "")
        {
           // alert("发生错误！\r\n错误码:" + dec2hex(e.number) + "\r\n" + "错误描述：" + e.description);
        }
        else
        {
           // alert("错误码:" + dec2hex(e.number));
        }
        ret = -1;
    }
  return ret;
}

function setRegisterInfo(registerInfo, registerType)
{
	var ret = 0;
	try
	{
		ret = CertControl.setRegisterInfo(registerInfo, registerType);
   }
    catch(e)
    {
        if (e.description != "")
        {
           alert("发生错误！\r\n错误码:" + dec2hex(e.number) + "\r\n" + "错误描述：" + e.description);
        }
        else
        {
           alert("错误码:" + dec2hex(e.number));
        }
        ret = -1;
    }
  return ret;
}