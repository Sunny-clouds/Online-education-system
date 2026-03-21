package com.onik.eduspring.factory;
import com.onik.eduspring.enums.FileTypeEnum;
import com.onik.eduspring.strategy.FileStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class FileStrategyFactory {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 获取对应策略
     */
    public FileStrategy getStrategy(String type) {

        FileTypeEnum fileTypeEnum = FileTypeEnum.getByType(type);

        if (fileTypeEnum == null) {
            throw new RuntimeException("未知文件类型");
        }

        return (FileStrategy) applicationContext.getBean(fileTypeEnum.getBeanName());
    }
}