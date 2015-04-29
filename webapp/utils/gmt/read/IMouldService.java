package packageSrc;

import java.util.List;
import voSrc;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

public interface IMouldService {
	@SuppressWarnings("unchecked")
	public MouldVo addMould(MouldVo mouldVo) throws GlobalException;;
	
	public boolean deleteMould (String[] ids) throws GlobalException;
	
	public boolean updateMould4Del(String[] ids) throws GlobalException;
	
	public boolean updateMould(MouldVo mouldVo) throws GlobalException;
	
	public MouldVo getMould(String id) throws GlobalException;
	
	public List<MouldVo> getMouldList(MouldVo mouldVo) throws GlobalException;
	
	public PageResult getMouldPR(MouldVo mouldVo,PageResult pageResult) throws GlobalException;
	
  }
