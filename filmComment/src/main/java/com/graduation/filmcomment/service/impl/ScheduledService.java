package com.graduation.filmcomment.service.impl;


import com.graduation.filmcomment.mapper.FilmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    @Autowired
    private FilmMapper filmMapper;
    /*** crontab文件的格式：S M H D m d cmd.
     * S: 秒（0-59）。 M: 分钟（0-59）。H：小时（0-23）。D：天（1-31）。m: 月（1-12）。d: 一星期内的天（1~7，1为星期天，7为星期六）。cmd: 要执行的命令。*/
    //   */5 * * * * ? ：每隔5秒执行一次
    //   0 */1 * * * ? ：每隔1分钟执行一次
    //   0 0 23 * * ? ：每天23点执行一次
    //   0 0 1 * * ? ：每天凌晨1点执行一次：
    //   0 0 1 1 * ? ：每月1号凌晨1点执行一次
    //   0 0 23 L * ? ： 每月最后一天23点执行一次
    //   0 0 1 ? * L ：每周星期天凌晨1点实行一次
    //   0 26,29,33 * * * ? ： 在26分、29分、33分执行一次
    //   0 0 0,13,18,21 * * ? ： 每天的0点、13点、18点、21点都执行一次

    @Scheduled(cron = "59 * * * * ?")
    public void intervalUpdateScore(){
        filmMapper.intervalUpdateScore();
    }
}
