package cn.labsoft.labos.utils.onlinepdf;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.labsoft.labos.framework.common.exception.GlobalException;

public class common extends Config {

	
	public boolean validParams(String path, String doc, String page){
		return !(	!doc.equals(basename(realpath(path))) ||
				 	doc.length() > 255 ||
				 	page.length() > 255
				);
	} 

	public String basename(String path){
		File f = new File(path);
		if(!f.exists())
			return "";
		int pos = path.lastIndexOf("\\");
		if(	!isWin()){
		    pos = path.lastIndexOf("/");
		}
		return path.substring(pos + 1);
	}

	public String realpath(String path){
		return new File(path).getAbsolutePath();
	}

	public void setCacheHeaders(HttpServletResponse response){
		response.setHeader("Cache-Control", "private, max-age=10800, pre-check=10800");
		response.setHeader("Pragma", "private");
		SimpleDateFormat RFC822DATEFORMAT = new SimpleDateFormat("EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z", Locale.US);
		response.setHeader("Expires", RFC822DATEFORMAT.format(new Date()));
	}

	public boolean endOrRespond(HttpServletRequest request, HttpServletResponse response){
		String mod = request.getHeader("If-Modified-Since");
		if(mod == null){
			return true;
		}
		response.setHeader("Last-Modified", mod);
		return false;
	}

	public String getForkCommandStart(){
		if(	isWin())
			return "START ";
		return "";
	}

	public String getForkCommandEnd(){
		if(	!isWin() )
			return " >/dev/null 2>&1 &";
		return "";
	}

	public int getTotalPages(String PDFPath) throws GlobalException {
		return super.getTotalPage(PDFPath);
	}

	@Override
	public String strip_non_numerics(String string) {
		return super.strip_non_numerics(string);
	}

	public int getStringHashCode(String string){
		string = string.trim();
		if(string == null || string.length() == 0)
			return 0;
		int hash = 0;
		for(int i = 0; i < string.length(); i++){
			hash = 31 * hash + string.charAt(i);
		}
		return hash;
    }

	public String mysql_real_escape_string(String sql){
		String ret = sql.trim();
		ret.replace("\\", "\\\\");
		ret.replace("\\\\\"", "\\\"");
		return ret;
	}

	public BufferedImage createResizedCopy(Image originalImage, 
            int scaledWidth, int scaledHeight, 
            boolean preserveAlpha) {
		int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
		Graphics2D g = scaledBI.createGraphics();
	    if (preserveAlpha) {
	    	g.setComposite(AlphaComposite.Src);
	    }
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null); 
	    g.dispose();
	    return scaledBI;
	}


	public void scale(String src, int width, int height, String dest) throws IOException {
		BufferedImage bsrc = ImageIO.read(new File(src));
		BufferedImage bdest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		bdest = createResizedCopy(bsrc, width, height, false);
		ImageIO.write(bdest,"png",new File(dest));
	}

	public String generateImage(String source_file, String cache_file, String resolution, String outformat) throws GlobalException {
		String extension = source_file.substring(source_file.lastIndexOf(".") + 1).toLowerCase();
		try {
			BufferedImage bimg = ImageIO.read(new File(source_file));
			int width          = bimg.getWidth();
			int height         = bimg.getHeight();
			int resol = 1;
			
			if(resolution!=null){
				try{
					resol = Integer.parseInt(resolution.trim());
				}catch(Exception e){
					resol = width;
					throw new GlobalException("" + e.getMessage());
				}
				if (width <= resol) {
					return source_file;
				}
			}else{
				resol = width;
			}
			
			double ratio      = (double)height / (double)width;
			int new_width  = resol;
			int new_height = (int)Math.ceil((new_width * ratio));
			BufferedImage bdest = new BufferedImage(new_width, new_height, BufferedImage.TYPE_INT_ARGB);
			bdest = createResizedCopy(bimg, new_width, new_height, false);
			ImageIO.write(bdest, extension, new File(cache_file));
		}catch(Exception e){
			cache_file = source_file;
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return cache_file;
	}
}