package work.hdjava.sample.common.exception.file;

import work.hdjava.sample.common.exception.base.BaseException;

/**
 * 文件信息异常类
 * 
 * @author soul
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
