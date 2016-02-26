package com.panther.http;

import com.google.common.collect.Maps;
import com.panther.stack.StackSpaceTest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * http example
 * Created by panther.dongdong on 2016/1/11.
 */
public class HttpTest {

    private static final Logger LOG = LoggerFactory.getLogger(HttpTest.class);
    private static HttpClientBuilder httpBulder = null;
    private static RequestConfig requestConfig = null;

    static {
        //设置http的状态参数
        try {
            int httpSoTimeOut = 1000000;
            int httpConnTimeOut = 1000000;
            requestConfig = RequestConfig.custom().setSocketTimeout(httpSoTimeOut).setConnectTimeout(httpConnTimeOut)
                    .build();
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
            int httpMaxConnection = 100;
            int httpDefConnection = 10;
            connectionManager.setMaxTotal(httpMaxConnection);
            connectionManager.setDefaultMaxPerRoute(httpDefConnection);
            httpBulder = HttpClients.custom();
            httpBulder.setConnectionManager(connectionManager);
        } catch (Throwable e) {
            LOG.error(e.getMessage(), e);
            throw new ExceptionInInitializerError("HttpClient init ERROR:" + e.getMessage());
        } finally {
            LOG.info("SmsHttpClient init [requestConfig=" + requestConfig + ",httpBulder=" + httpBulder + "]");
        }
    }

    public static void main(String[] args) {
//        String type = "'qunar_train','qunar_monitor_debug','qunar_flight','qunar_visa','qunar_finance','qunar_productt','qunar_emptytest','test','qunar2','qunarta','kp178','qunar_tts','qunar_cmcc_test','qunar_productn','jp51hcwy','aftaaa','chunqiu','xdfjp','gjyxho','jipiao365','gjepaioiao','jp86air','epaipiao','et9988','fefei-air','guangfaok','lh-air','njjp','sosolx','tttjvip','wingswin','jp51qunar','chailvba','ept360','jp020newsky','quicku','xllxw','ygair','jiazhoutrip','fly-piao','tianyilxw','feijipiao','jp0532air','yeefly','jp82255555','PW168','xqbsl','pmtx','hetian','konglv','shanglv','puhui','aohang','qsdjp','159net_3663','chalvtong','xiaoyaoxing','taopiao8','huazheng','junhang','juncheng','ymlxw','piaobaobao','laihuifei','pingjiahk','dfxy','youyoutong','jp8','chupiao','bjygzl','qunar_qdjpcn','njzrhk','tianxiang','shangyouwang','qunar_hotel5','gzsj','jyq','cdqy','ygjp','shbyc','wysl','gdgl','yctx','fjql','htpw','xhrpw','hhhk','cxwy','34934598','wgyg','JiXiangAir','jxsl','hxhkdc','zailushang','qunar_ta_test','kwe108','shenyangtianyu','shjpj','kmyy','jtpw','hhmy','shby','bibikan','hanyuan','shcty','zhenpiaowang','etpass','chengfeng','jslhk','tongchengwang','xindongfang','159net_3166','sxy','huangjin','xllxw_bkp','hengsy','sztdj','kepuair','tianyi','yhx','szhk','bjspek','jgl','cdjchk','jxqc888_bkp','xintianyu','ysd','wxslw','tbgjkkx','zhkzsl','hydhk','gtylxw','gllxw','qqlxw','sjpw','ltlxw','pbw','qqsl','pglxw','bysc','mgw','jlhk','yunfhk','pysl','yshk','ygzl','yylxw','ycwy','ylwy','pwlxw','cxw','pflx','jxbird','hytx','yunxiang','lvdajie','junchen02','tclxw','hxzl','jinrifei','qxhk','hualianhk','hcwy','jplxw','feiyihk','xianghehk','xingtu','guoanpw','yinliansl','wypw','ydlxw','guangyouly','aokair','happyfly','dzsl','sjpiaowu','ldlxw','siying','htsl','cglxw','txjhk','longxhk','hongqsl','tfhk','jidahk','sxtx','qunar_flight_tuiguang','tonghang','nbzhy','cmder','ltw','nanfeiw','bdslw','tjslv','hflxw','ylvhf','sosojp','jp123','brblv','tdhui','tiehangslv','ehangtx','wujislv','qclxw','qqhkpw','hthk','yunyouslw','gxhk','konggang','brotherlxw','hangyislv','napiaow','sopw','lytxlxw','yifeihl','jitonggj','xinganxian','yichengtong','chenhuisl','f_cxjz','f_qqlxw','yichuxing','f_5115fly','f_sbl','f_xzxjp','f_whhk','f_xcslw','f_hclxw','f_kyhk','f_fblxw','f_lghk','f_dhjs','f_asdlxw','f_958jp','f_sshk','f_jtgl','f_hxsl','f_tslxw','f_tchk','f_rnf','f_yyhf','f_edlxw','f_ylf','f_dzchk','f_axlxw','f_160jp','f_yjw','f_ptw','f_ttfjpw','f_xtlxw','f_xhhk','f_bfjr','f_fyjpw','f_djlhk','f_yahk','f_hylxw','f_qfhk','f_yhsw','f_ylmc','f_tysl','f_cdtd','f_jpsl','f_htlyw','f_ytx','f_kmhq','f_myhf','f_hqlxw','f_tjjpw','f_tczl','f_hthf','f_xtsl','f_hysl','f_zcsl','f_xfhf','f_ghhk','f_tphksl','f_qylxw','f_hyt','f_jxtjjp','f_wlxdg','f_fkslw','f_hchk','f_wthk','f_yfdjw','f_yxwy','f_nthk','f_fzpw','f_cdjy','f_xtx','f_shsl','f_haichk','f_xrhk','f_pflxwdg','f_txjdw','f_zyfxsl','f_nhglw','f_hongchk','f_dalxw','f_hnsl','f_jxhk','f_sjlxw','f_hxtx','f_xflxw','f_jyhkpw','f_wnh','f_kmlfhk','f_ftelxw','f_hyslw','f_thsl','f_qblxw','f_fmhk','f_sgzl','f_jltx','f_xthk','f_dyhk','f_kjsl','f_xsl','f_gyjrhk','f_yhhk','f_tctx','f_yyhk','f_typw','f_bczc','f_lfhk','f_lyylxw','f_ysl','f_shhk','f_gblhk','f_glhf','f_lhhlxw','f_xysz','f_bnszlx','f_atw','f_ftlxw','f_htslw','f_yyihk','f_mpw','f_xzlslw','f_byhk','f_cytx','f_tianxsw','f_cltxyd','f_ddfsl','f_rzl','f_jhly','f_qczl','f_szdd','f_ypt','f_qysl','f_pdhk','f_fdslw','f_hthk','f_ldlxw','f_klslw','f_wdhk','f_ylw','f_xmhyslw','f_dshk','f_cfmhspc','f_hldlxw','f_slsl','f_yjs','f_ydslw','f_zlyxds','f_sjf','f_hyhk','f_lxj','f_hjwy','f_zthk','f_xzhk','f_smjpw','f_qdlxw','f_lxtxw','f_dzhk','f_shyhhk','f_1xxlxw','f_mhjp','f_jlfxw','f_rtlyw','f_1tpw','f_jzhk','f_cysl','f_fyzcjpw','f_zmkmhk','f_mlgl','f_zfslw','f_ltlxw','f_hzac','f_lylt','f_shjq','f_yfslw','f_yzw','f_xhly','f_xmbstslw','f_fylxw','f_jkqhk','f_ryxslw','f_1jdlxw','f_jpgj','f_tlw','qunar_uc_flight','f_tttjslw','f_yh168','f_jyzl','f_ssw','f_mklx','f_ezsl','f_scsl','f_hzl','f_cylt','qunar_flight_change','f_mhzx','f_ellx','f_asfslw','f_ylpa','f_xtzl','f_xspw','f_51f','f_ndslw','f_hhzl','f_yyx','f_lyhk','f_ymzl','f_wmlxw','f_mgsl','f_szfhhk','f_cdaypw','f_qxlxw','f_hqclxs','f_hshk','f_xaslw','f_dthl','f_wphk','f_atslw','f_paslw','f_wwlxw','f_yzjpw','f_sldhk','f_rhsl','f_zhslxw','f_yzl','f_hxdpw','f_wcl','f_tdyj','f_pyjpw','f_zylxw','f_jqlslw','f_2zyhk','f_tblxw','f_1hzkhhf','f_1bjzhl','f_ydtslw','f_xysl','f_hyd','f_jylxw','f_jjtjpw','f_qdyfhk','f_cdhdhk','f_1asfgjjp','f_kmhlpw','f_cmdlxw','f_hdlxw','f_cqzax','f_cjfslw','f_1xyy','f_hdbs','f_alzgslzx','f_wlpww','f_jyhhk','f_zglxs','f_tcslw','f_yntb','f_kmtjjpw','f_jhhk','f_1sshk','f_lcpwzx','f_xssl','f_jjlxw','f_xwyslw','f_1jzhhf','f_hdlc','f_sztp','f_jy','f_hytssl','f_tdtx','f_wqslw','f_ykf','f_ydhk','f_hytk','f_1hthk','f_hxslw','f_cclxw','f_qcsz','f_khhyt','f_zyxhk','f_bylxw','f_2jxhk','f_klcx','f_ralhk','f_wstlxw','f_xtxth','f_p185jpw','f_jxsl','f_kllx','f_jpw','f_ppy','f_dlsw','f_jyhk','f_sjhk','f_jxbl','f_mtjpw','f_zzystj','f_shysl','f_dzrhl','f_hlcy','f_ohjd','f_kmgrhk','f_dhahk','f_yfhkdg','f_1mpw','f_cme','f_tzhkpw','f_2xylxw','f_xgtjjpw','f_xfbslw','f_95160jlw','f_blsl','f_1hyhk','f_yllxw','f_97jpsc','f_msslw','f_ccqtravel','f_ezour','f_kuaida','f_mysl','f_pplxw','f_yylvw','f_lebangli','f_ydgjhk','f_daqianlxw','f_1xslhk','f_1qsjq','f_tcsl','f_dchk','f_lapw','f_tdx','f_qxpw','f_lshk','f_gqdslw','f_fyw','f_kdslw','f_1xspw','f_klcf','f_xxslw','f_njsl','f_wxlhk','f_fcw','f_jcslw','f_njxtpw','f_1sjhk','f_qklxw','f_yksl','f_fxslw','f_qmlxw','f_mxlcw','f_qthk','f_hzchhk','f_xcpw','f_yhy','f_hcsl','f_pplxwtj','f_jrgzlxw','f_xhlydg','f_azltw','f_1fylxw','f_1hxslw','f_nyhk','f_jphk','f_jysl','f_yalxw','f_brhk','f_cxlxw','f_ttsl','f_ytlxwtj','f_xqchk','f_yxzl','f_eair_zx','f_klfz','f_jzl','f_lhw','f_xscsw','f_djgj','f_tjslw','f_jclxw','f_ydlxw','f_xysltj','f_fxzl','f_1ttjq','f_jdlx','f_thtx','f_xyw','f_qlym','f_klextj','f_jylx','f_xtwdg','f_xhhktj','f_gtly','f_prpw','f_1hysd','f_jwlxw','f_zhslw','f_mmx','f_hktl','f_xjhk','f_bxj','f_hasl','f_drm','f_1zhslw','f_xzl','f_jiy','f_zysl','f_shd','f_htg','f_whk','f_xyz','f_ygx','f_xky','f_tyx','f_ysy','f_dhm','f_wjd','f_btd','f_fnd','f_fxd','f_jrd','f_lxq','f_whs','f_hud','f_wys','f_psl','f_ysf','f_ytd','f_jts','f_sat','f_jtw','f_hlx','f_slj','f_bad','f_wyj','f_hny','f_bsl','f_xhc','f_yzm','f_ysz','f_yhsl','f_txp','f_hah','f_ayt','f_nfd','f_jpi','f_mzj','f_dyg','f_hks','f_tpy','hotel_mxlx','f_chs','f_xal','f_flh','f_pmt','f_hlk','f_bal','f_yhj','f_thk','f_qxx','f_lds','f_cit','f_xhx','f_jls','f_box','f_xby','f_jla','q_flight_monitor','f_kzf','f_zyo','f_bnx','f_phs','f_jiz','f_zss','f_fny','f_bhs','f_cui','f_xlv','f_crs','f_yox','f_nyh','f_san','f_syf','f_dzo','f_hdx','f_qch','f_yax','f_mgo','f_afo','f_zhl','f_zhg','f_sxs','f_swg','f_xyn','f_myl','f_txl','f_cph','f_jhk','f_klq','f_kls','f_xsf','f_kyx','f_rer','f_rwf','f_afk','f_yhk','f_wxy','f_xtl','f_zdh','f_pgy','f_xly','f_lpg','f_yxz','f_bbx','f_yrh','f_znh','f_tef','f_qyo','f_liy','f_hqo','f_slf','f_cxo','f_klx','f_fda','f_fyx','f_pia','f_mrd','f_jyj','f_ncx','f_xgl','f_wsc','f_yda','f_gyx','f_yhh','f_syx','f_yxj','f_lxz','f_wya','f_sly','f_ych','f_ylb','f_ado','f_xto','f_loogair','f_alx','f_tua','f_zro','f_fyw1','f_xdh','f_tfp','f_swl','f_fes','f_sda','f_qqt','f_mih','qunar_tts_lost','f_ynk','f_zlw','f_cos','f_dyx','f_yso','f_wjx','f_yqs','f_ghw','f_yqo','f_ydu','f_ldo','f_xkh','f_xhr','f_yoo','f_ypl','f_yck','f_hsl','f_jyx','f_fxo','f_hqy','f_zhv','f_myz','f_ybs','f_cxy','f_xim','f_wjy','f_dha','f_cejsair','f_gdl','f_hqx','f_qyl','f_int','f_zlx','f_tbo','f_zdh1','f_huf','f_kys','f_quv','f_quz','f_hzc','f_jya','f_jxy','f_sdx','f_yle','f_hsj','f_aqh','f_yux','f_fce','f_lyo','f_glo','f_xlf','f_hms','f_zly','f_nyx','f_hxu','f_ydv','f_hxv','f_qdair','f_jmo','f_lyp','f_jwe','f_xzj','f_qxu','f_szx','f_srx','f_zwe','f_xhv','f_yha','f_zgo','f_ltn','f_nhs','f_tex','f_jhu','f_eda','f_hxn','f_xzy','f_pyh','f_xzy1','f_spw','f_das','f_bjy','qunar_fuwu_gaiqian','qunar_fuwu_refund_proof','f_yte','f_tqa','f_wxl','f_baj','f_azl','f_htz','f_ytn','f_htv','f_jah','f_hlz','f_fxa','f_syu','f_ylo','f_ygz','f_shm','f_yly','f_zit','f_ljw','f_htb','f_fhl','f_ydj','f_ytc','f_myk','f_zgm','f_lys','f_wsx','f_yuo','f_sfs','f_bnm','qunar_xhr','f_skl','f_sds','f_txb','f_fka','f_qyp','f_csw','f_nlh','f_sxe','f_ppa','qunar_youxian','qunar_flight_tuiguang_1','f_ztl','f_pma','f_ktw','f_rfs','f_rfw','f_xfx','f_kfw','f_hwl','f_xyb','f_hwa','f_sxh','f_ztx','f_klg','f_zgd','f_ttz','f_pga','f_tas','f_kqh','f_fxs','f_hye','f_pch','f_fxz','f_lme','f_xcp','f_bta','f_wyc','f_afa','f_sfa','f_xyd','qunar_fuwu_refund_xcd','f_fxb','f_qtx','f_bfj','f_hfb','f_yxb','f_ljx','f_njk','f_ylq','f_tll','f_fnb','f_qdc','f_pyj','f_dfx','f_bha','f_ths','f_yhb','f_hij','f_cfl','f_yya','f_fcq','f_xfg','f_zsr','f_llf','f_zyg','f_xcl','f_btl','f_qxm','f_jsb','f_ycl','f_fys','f_drr','f_tth','f_tfq','f_ydp','f_cyl','f_btb','f_sba','f_ltl','f_zjp','f_zyk','f_jfh','f_jzb','f_jxu','f_xku','f_xjs','f_xxz','f_bhl','f_jzr','f_ass','f_rwu','f_lyw','f_yfa','f_hac','qunar_fm_touch','f_mxs','f_gef','f_ltr','f_xjl','f_qtq','f_fcs','f_xse','f_dhs','f_ebr','f_czl','f_fzy','f_tgh','f_dmz','f_kxz','f_sla','f_knd','f_whh','f_rny','f_klh','f_sxc','f_ytj','f_qqa','f_fla','f_tha','f_fle','f_yyc','f_txd','f_kns','f_yfl','f_smf','f_afd','f_afx','f_lxs','f_qcq','f_grf','f_xrk','f_hal','f_kgp','f_xte','f_zyz','f_lbx','f_zhh','f_yqz','f_haf','f_hjy','f_yhf','f_ree','f_jlw','f_fxh','f_rhm','f_zsb','f_zsa','f_hwb','f_dqa','f_hxa','f_xyg','qunar_qproxy','f_fts','f_jyv','f_lzy','f_dfg','f_yza','f_jhv','f_yfx','f_mth','f_tsh','f_fjs','f_yingan','f_ssl','f_zbh','f_fdl','f_jqw','f_tys','f_sbz','f_tlh','f_xcb','f_zsp','f_mfs','f_tca','f_qcb','f_qte','f_tyb','f_jlb','f_ruili','f_jlf','f_xoa','f_ssj','f_gwz','f_sxa','f_byj','f_jql','f_xsg','f_xfz','f_ver','f_xaa','f_ylk','f_yhc','f_xvr','f_rxs','f_cgs','qunar_ftet_confirm','f_zds','f_bmn','f_hsa','f_xgx','f_yps','f_ypk','f_ypy','f_ypm','f_ypx','f_ypr','f_ypi','f_ypb','f_ypf','f_ysc','f_nba','f_jpg','f_bys','f_cye','f_jcb','f_lyv','f_xad','f_xia','f_bfa','hotel_t3ym','f_nbb','f_tyf','f_tfa','f_xkz','f_jzf','f_uge','f_rcs','f_qxk','f_yxc','f_tyr','f_jhp','f_yxd','f_typ','f_azu','f_nmh','f_rhe','f_xae','f_axx','f_etb','f_pxn','f_xsz','f_tye','f_czc','f_czf','f_fyb','f_haz','f_pjs','f_cyj','f_hxr','f_bsx','f_hgs','f_yyd','f_uru','f_dfn','f_sxb','f_yas','f_kfz','f_xjn','f_uug','f_pya','f_yln','f_hft','f_hxf','f_kpj','qunar_f_tes','f_obe','f_zpq','qunar_flight_u','f_glm','f_hrs','f_hda','f_lcj','f_tsl','f_ktc','f_rzh','f_cxa','f_pyg','f_zgc','f_ylr','f_yhp','f_zcg','f_sfd','f_sjs','f_yxf','f_zln','f_yse','f_czg','f_slc','f_wmd','f_bcr','qunar_travel_fr','f_ygc','f_yng','f_dyp','f_zpw','f_xhm','f_fbl','f_dzl','f_ytu','f_hqn','qunar_f_dzl','f_yyl','f_pcw','qunar_qtp','f_hcg','f_dbs','qunar_airline','airline_onebyone','qunar_f_pcw','qunar_f_zpw','f_bsh','qunar_flygo','qunar_f_dbs','f_fye','f_clw','f_qdg','f_yze','f_htq','f_yov','f_yxk','f_jjs','f_hmk','f_kfn','qunar_f_hmk','qunar_f_yxk','qunar_f_htq','f_lxm','f_qth','qunar_lxm','qunar_f_qth','qunar_f_yaq','f_sma','f_sfw','f_tan','f_fhs','f_yau','f_kxy','f_yaq','qunar_f_xjw','qunar_f_xjb','qunar_f_kxy','qunar_f_kxa','qunar_f_bxp','qunar_f_bxb','f_ppq','f_jtl','f_ytz','f_cqa','f_fza','f_yfj','qunar_f_yzb','f_yzb','f_szb','f_zaf','f_qhh','f_xbh','f_xdu','f_rha','f_mjl','f_yph','f_jjb','qunar_f_service','f_pdg','f_cyn','f_yay','f_jyg','f_tcg','f_hbk','f_tsa','f_ats','f_xce','qunar_f_hbk','qunar_f_jjb','qunar_f_yph','qunar_f_szb','qunar_f_ppq','qunar_f_cyn','qunar_f_jtl','qunar_f_jjs','qunar_f_mjl','qunar_f_zaf','qunar_f_qha','qunar_f_qhb','qunar_f_yaz','qunar_f_yba','qunar_f_tsa','qunar_f_ats','f_dya','f_lcb','qunar_f_fhs','f_ygk','f_rya','f_gzb','f_zya','f_sys','qunar_f_ygk','qunar_f_ygf','qunar_f_tcg','f_sjb','f_szj','f_ztc','f_rxp','f_Jce','f_yxm','f_ccs','qunar_f_zya','qunar_f_zye','qunar_f_sjb','qunar_f_hbi','qunar_f_hri','qunar_f_yxm','qunar_f_ccs','qunar_f_rya','f_tjm','qunar_f_thf','qunar_f_thj','qunar_f_gzb','f_asv','qunar_f_rha','qunar_f_wlm','qunar_f_cyp','qunar_f_caa','qunar_f_yhr','qunar_f_yhu','qunar_f_sya','qunar_f_syb','qunar_f_fhg','qunar_f_fhc','qunar_f_xxj','qunar_f_xxa','f_zym','f_wlm','f_hqm','f_xsk','f_bdd','f_tqb','f_fpw','f_lzl','qunar_f_bdd','qunar_f_jyg','qunar_f_hqm','qunar_f_xsk','f_zcp','f_xck','qunar_f_hih','qunar_f_hrq','qunar_f_fpw','qunar_f_jfi','f_mxc','qunar_f_szj','qunar_f_tqb','f_xqg','f_rsg','qunar_f_jym','qunar_f_jyr','f_wca','f_xap','f_ybw','f_szd','qunar_f_rsg','qunar_f_rsa','qunar_f_xap','f_flf','f_jyk','f_yzc','f_lyx','qunar_f_wme','f_dph','f_hhc','qunar_f_dj1','qunar_f_dj2','qunar_f_dph','qunar_f_a3bd','f_xtk','f_wme','f_dzf','f_frl','f_zhc','f_dpn','qunar_f_mxc','f_kln','f_slv','qunar_f_dpn','qunar_f_lzl','qunar_f_zcp','qunar_f_flf','qunar_f_hhc','qunar_f_lyx','qunar_f_yzc','qunar_f_jyk','qunar_f_hdn','qunar_f_yze','qunar_f_hxy','f_chi','f_hdn','f_hxy','f_xar','f_cap','qunar_f_hdm','qunar_f_yfm','qunar_f_yfn','qunar_f_kln','f_xjc','f_hdm','qunar_f_hbm','qunar_f_dhl','qunar_f_ftd','qunar_f_adh','qunar_f_ybb','qunar_f_ybc','qunar_f_ybd','qunar_f_ybw','f_adh','f_ftd','f_hbm','qunar_f_xjd','qunar_f_xar','qunar_f_dts','qunar_f_bes','f_dhl','f_bes','f_dts','f_xjd','f_slm','f_sxg','f_xjx','qunar_f_yfg','qunar_f_yfk','f_skb','qunar_f_hbo','qunar_f_mwh','qunar_f_ada','qunar_f_adb','f_mwh','f_hbo','f_kma','f_afe','f_lzm','f_xje','f_szg','qunar_f_rxp','f_asx','f_qnc','f_jdp','f_lcc','qunar_f_yma','qunar_f_xck','qunar_f_xcr','qunar_f_xcq','qunar_f_txc','qunar_f_dma','qunar_f_dmb','qunar_f_xmz','qunar_f_xma','qunar_f_cym','qunar_f_cyw','f_txc','f_yma','f_tfx','f_zaa','f_rcl','qunar_f_lzm','qunar_f_xje','qunar_f_xjg','qunar_f_xjf','qunar_f_jdp','qunar_f_fyo','qunar_f_wmj','qunar_f_lxd','qunar_f_fyn','qunar_f_tfx','qunar_f_zaa','f_lxd','f_tyv','f_fyn','qunar_f_xyx','qunar_f_xav','qunar_f_xaw','qunar_f_slm','f_fyo','qunar_f_xau','qunar_f_frl','qunar_f_fra','qunar_f_frb','qunar_f_fte','f_fte','f_kww','qunar_f_bdt','qunar_f_bde','qunar_f_kww','f_wda','f_fhd','f_tyu','f_hpc','qunar_f_zcs','qunar_f_mhl','f_wmj','qunar_f_hpc','f_zcs','f_bdt','f_tyy','f_bde','f_qnd','f_jxf','f_xau','qunar_f_hcj','qunar_f_hca','qunar_f_xfd','qunar_f_xfe','qunar_f_afe','qunar_f_mxa','qunar_f_fhd','qunar_f_lcc','qunar_f_tyu','f_yxn','qunar_f_yzd','qunar_f_yjs','qunar_f_wnw','qunar_f_hpg','qunar_f_hpb','f_mxa','f_ztm','f_lxk','f_wnw','qunar_f_hqu','qunar_f_hqv','qunar_f_sym','qunar_f_syn','sms_test','qunar_f_yxn','qunar_f_qcy','qunar_flight_finance','qunar_f_hdj','qunar_f_abs','qunar_f_wmq','qunar_f_xlq','qunar_f_xlj','f_yzd','f_wmq','f_slx','f_zhm','f_kdh','f_xtj','f_gyz','f_abs','f_pgv','qunar_f_jyy','qunar_f_slx','f_bjb','f_hdj','f_jyy','f_yjo','qunar_f_jhn','qunar_f_zhm','qunar_f_zhn','qunar_f_hxo','qunar_f_hxp','f_jhn','f_hxo','f_zab','f_jyz','f_jpn','f_xma','qunar_f_hnn','qunar_f_hne','qunar_f_fda','qunar_f_bsh','qunar_f_mla','qunar_f_mlb','f_txq','qunar_f_ssb','qunar_f_jyz','qunar_f_cgp','qunar_f_hcd','qunar_f_jzj','f_jzj','f_ywv','f_hhs','f_ssb','f_hcd','f_ltg','f_cgp','qunar_f_rp','f_jtj','qunar_f_gyz','f_fyr','qunar_f_jxf','qunar_f_fyr','qunar_f_wmm','f_wmm','f_ffg','qunar_f_zab','qunar_f_jlc','qunar_f_tac','qunar_f_yfq','qunar_f_hhs','f_tac','f_jlc','f_yfq','qunar_f_pfa','qunar_f_pff','qunar_f_dcz','qunar_f_dcj','qunar_f_yzf','qunar_f_yzq','qunar_f_bcs','qunar_f_bca','qunar_f_sxu','f_dcj','qunar_f_ztm','qunar_f_daa','qunar_f_dae','f_pfa','qunar_f_rxj','qunar_f_xfs','qunar_f_rcv','qunar_f_rcr','qunar_f_ybp','qunar_f_hlj','qunar_f_hla','qunar_f_syc','qunar_f_xba','qunar_f_mka','qunar_f_mbq','qunar_f_xjp','qunar_f_xfp','qunar_f_jml','qunar_f_hna','qunar_f_hnb','qunar_f_hnc','f_hla','f_hlj','f_syc','f_xfs','qunar_f_wts','qunar_f_ybr','qunar_f_ybt','qunar_f_dzg','qunar_f_dzj','qunar_f_taa','qunar_f_zad','qunar_f_ybx','qunar_f_ltg','qunar_f_ybn','qunar_f_dya','f_fyc','f_tpc','qunar_f_zsf','qunar_f_fyu','f_dmc','f_bhb','f_qxa','qunar_f_zse','qunar_f_ddb','qunar_f_yfu','qunar_f_xhp','qunar_f_yzi','qunar_f_yay','qunar_price_l','f_ycd','f_yys','qunar_f_bdf','qunar_f_hjs','qunar_f_cfr','qunar_f_fzf','qunar_f_ycf','f_bdf','qunar_f_rnf','qunar_f_yce','f_hul','qunar_f_yfr','qunar_f_xbd','qunar_f_xjm','f_jhd','qunar_f_ycm','qunar_f_qck','qunar_f_zae','qunar_f_tmy','qunar_f_yco','qunar_f_kcl','qunar_f_djb','qunar_f_zlo','qunar_f_hbw','qunar_f_ddc','f_chh','f_alc','qunar_f_xds','qunar_f_taf','qunar_f_ycr','f_afm','f_yzj','f_zto','f_kna','qunar_f_qcl','qunar_f_mlz','qunar_f_lyf','qunar_f_fhe','qunar_ticket','qunar_f_htr','qunar_f_mkb','qunar_f_zge','qunar_f_qhs','qunar_f_cft','qunar_f_ygq','qunar_f_yxp','f_xcx','qunar_f_ftg','f_sxx','qunar_f_ddl','qunar_f_yje','qunar_f_dbh','qunar_f_dba','qunar_f_ywx','qunar_f_cjz','qunar_f_ywp','qunar_f_qhc','qunar_f_jhd','qunar_f_dyf','qunar_f_gmh','qunar_f_yec','qunar_f_blt','qunar_f_xzx','qunar_f_zxd','f_tyh','qunar_f_phh','qunar_f_lxe','qunar_f_xkx','qunar_f_hsb','qunar_f_hsc','qunar_f_syp','qunar_f_fdp','qunar_f_dqc','qunar_ota_auth','qunar_f_tqc','qunar_f_kxl','qunar_f_ycx','qunar_f_dds','qunar_f_ttk','qunar_f_bym','qunar_f_jns','qunar_f_byh','qunar_f_nnt','qunar_f_qyf','qunar_f_lmd','qunar_f_ycp','qunar_f_ldz','f_pge','qunar_f_dgx','f_qmp','f_wha','f_wxa','qunar_f_tfl','qunar_f_xfz','f_xfk','qunar_f_yxv','qunar_f_yxq','qunar_f_fcb','qunar_f_kya','qunar_f_sug','qunar_f_zag','qunar_f_tah','qunar_f_abx','qunar_f_mhs','f_saz','qunar_f_rss','qunar_f_tdd','f_gxa','qunar_f_xbi','qunar_f_qcm'";
        String type = "'qunar_train','qunar_monitor_debug','qunar_flight','qunar_visa','qunar_finance','qunar_productt','qunar_emptytest','test','qunar2','qunarta','kp178','qunar_tts','qunar_cmcc_test','qunar_productn','jp51hcwy','aftaaa','chunqiu','xdfjp','gjyxho','jipiao365','gjepaioiao','jp86air','epaipiao','et9988','fefei-air','guangfaok','lh-air','njjp','sosolx','tttjvip','wingswin','jp51qunar','chailvba','ept360','jp020newsky','quicku','xllxw','ygair','jiazhoutrip','fly-piao','tianyilxw','feijipiao','jp0532air','yeefly','jp82255555','PW168','xqbsl','pmtx','hetian','konglv','shanglv','puhui','aohang','qsdjp','159net_3663','chalvtong','xiaoyaoxing','taopiao8','huazheng','junhang','juncheng','ymlxw','piaobaobao','laihuifei','pingjiahk','dfxy','youyoutong','jp8','chupiao','bjygzl','qunar_qdjpcn','njzrhk','tianxiang','shangyouwang','qunar_hotel5','gzsj','jyq','cdqy','ygjp','shbyc','wysl','gdgl','yctx','fjql','htpw','xhrpw','hhhk','cxwy','34934598','wgyg','JiXiangAir','jxsl','hxhkdc','zailushang','qunar_ta_test','kwe108','shenyangtianyu','shjpj','kmyy','jtpw','hhmy','shby','bibikan','hanyuan','shcty','zhenpiaowang','etpass','chengfeng','jslhk','tongchengwang','xindongfang','159net_3166','sxy','huangjin','xllxw_bkp','hengsy','sztdj','kepuair','tianyi','yhx','szhk','bjspek','jgl','cdjchk','jxqc888_bkp','xintianyu','ysd','wxslw','tbgjkkx','zhkzsl','hydhk','gtylxw','gllxw','qqlxw','sjpw','ltlxw','pbw','qqsl','pglxw','bysc','mgw','jlhk','yunfhk','pysl','yshk','ygzl','yylxw','ycwy','ylwy','pwlxw','cxw','pflx','jxbird','hytx','yunxiang','lvdajie','junchen02','tclxw','hxzl','jinrifei','qxhk','hualianhk','hcwy','jplxw','feiyihk','xianghehk','xingtu','guoanpw','yinliansl','wypw','ydlxw','guangyouly','aokair','happyfly','dzsl','sjpiaowu','ldlxw','siying','htsl','cglxw','txjhk','longxhk','hongqsl','tfhk','jidahk','sxtx','qunar_flight_tuiguang','tonghang','nbzhy','cmder','ltw','nanfeiw','bdslw','tjslv','hflxw','ylvhf','sosojp','jp123','brblv','tdhui','tiehangslv','ehangtx','wujislv','qclxw','qqhkpw','hthk','yunyouslw','gxhk','konggang','brotherlxw','hangyislv','napiaow','sopw','lytxlxw','yifeihl','jitonggj','xinganxian','yichengtong','chenhuisl','f_cxjz','f_qqlxw','yichuxing','f_5115fly','f_sbl','f_xzxjp','f_whhk','f_xcslw','f_hclxw','f_kyhk','f_fblxw','f_lghk','f_dhjs','f_asdlxw','f_958jp','f_sshk','f_jtgl','f_hxsl','f_tslxw','f_tchk','f_rnf','f_yyhf','f_edlxw','f_ylf','f_dzchk','f_axlxw','f_160jp','f_yjw','f_ptw','f_ttfjpw','f_xtlxw','f_xhhk','f_bfjr','f_fyjpw','f_djlhk','f_yahk','f_hylxw','f_qfhk','f_yhsw','f_ylmc','f_tysl','f_cdtd','f_jpsl','f_htlyw','f_ytx','f_kmhq','f_myhf','f_hqlxw','f_tjjpw','f_tczl','f_hthf','f_xtsl','f_hysl','f_zcsl','f_xfhf','f_ghhk','f_tphksl','f_qylxw','f_hyt','f_jxtjjp','f_wlxdg','f_fkslw','f_hchk','f_wthk','f_yfdjw','f_yxwy','f_nthk','f_fzpw','f_cdjy','f_xtx','f_shsl','f_haichk','f_xrhk','f_pflxwdg','f_txjdw','f_zyfxsl','f_nhglw','f_hongchk','f_dalxw','f_hnsl','f_jxhk','f_sjlxw','f_hxtx','f_xflxw','f_jyhkpw','f_wnh','f_kmlfhk','f_ftelxw','f_hyslw','f_thsl','f_qblxw','f_fmhk','f_sgzl','f_jltx','f_xthk','f_dyhk','f_kjsl','f_xsl','f_gyjrhk','f_yhhk','f_tctx','f_yyhk','f_typw','f_bczc','f_lfhk','f_lyylxw','f_ysl','f_shhk','f_gblhk','f_glhf','f_lhhlxw','f_xysz','f_bnszlx','f_atw','f_ftlxw','f_htslw','f_yyihk','f_mpw','f_xzlslw','f_byhk','f_cytx','f_tianxsw','f_cltxyd','f_ddfsl','f_rzl','f_jhly','f_qczl','f_szdd','f_ypt','f_qysl','f_pdhk','f_fdslw','f_hthk','f_ldlxw','f_klslw','f_wdhk','f_ylw','f_xmhyslw','f_dshk','f_cfmhspc','f_hldlxw','f_slsl','f_yjs','f_ydslw','f_zlyxds','f_sjf','f_hyhk','f_lxj','f_hjwy','f_zthk','f_xzhk','f_smjpw','f_qdlxw','f_lxtxw','f_dzhk','f_shyhhk','f_1xxlxw','f_mhjp','f_jlfxw','f_rtlyw','f_1tpw','f_jzhk','f_cysl','f_fyzcjpw','f_zmkmhk','f_mlgl','f_zfslw','f_ltlxw','f_hzac','f_lylt','f_shjq','f_yfslw','f_yzw','f_xhly','f_xmbstslw','f_fylxw','f_jkqhk','f_ryxslw','f_1jdlxw','f_jpgj','f_tlw','qunar_uc_flight','f_tttjslw','f_yh168','f_jyzl','f_ssw','f_mklx','f_ezsl','f_scsl','f_hzl','f_cylt','qunar_flight_change','f_mhzx','f_ellx','f_asfslw','f_ylpa','f_xtzl','f_xspw','f_51f','f_ndslw','f_hhzl','f_yyx','f_lyhk','f_ymzl','f_wmlxw','f_mgsl','f_szfhhk','f_cdaypw','f_qxlxw','f_hqclxs','f_hshk','f_xaslw','f_dthl','f_wphk','f_atslw','f_paslw','f_wwlxw','f_yzjpw','f_sldhk','f_rhsl','f_zhslxw','f_yzl','f_hxdpw','f_wcl','f_tdyj','f_pyjpw','f_zylxw','f_jqlslw','f_2zyhk','f_tblxw','f_1hzkhhf','f_1bjzhl','f_ydtslw','f_xysl','f_hyd','f_jylxw','f_jjtjpw','f_qdyfhk','f_cdhdhk','f_1asfgjjp','f_kmhlpw','f_cmdlxw','f_hdlxw','f_cqzax','f_cjfslw','f_1xyy','f_hdbs','f_alzgslzx','f_wlpww','f_jyhhk','f_zglxs','f_tcslw','f_yntb','f_kmtjjpw','f_jhhk','f_1sshk','f_lcpwzx','f_xssl','f_jjlxw','f_xwyslw','f_1jzhhf','f_hdlc','f_sztp','f_jy','f_hytssl','f_tdtx','f_wqslw','f_ykf','f_ydhk','f_hytk','f_1hthk','f_hxslw','f_cclxw','f_qcsz','f_khhyt','f_zyxhk','f_bylxw','f_2jxhk','f_klcx','f_ralhk','f_wstlxw','f_xtxth','f_p185jpw','f_jxsl','f_kllx','f_jpw','f_ppy','f_dlsw','f_jyhk','f_sjhk','f_jxbl','f_mtjpw','f_zzystj','f_shysl','f_dzrhl','f_hlcy','f_ohjd','f_kmgrhk','f_dhahk','f_yfhkdg','f_1mpw','f_cme','f_tzhkpw','f_2xylxw','f_xgtjjpw','f_xfbslw','f_95160jlw','f_blsl','f_1hyhk','f_yllxw','f_97jpsc','f_msslw','f_ccqtravel','f_ezour','f_kuaida','f_mysl','f_pplxw','f_yylvw','f_lebangli','f_ydgjhk','f_daqianlxw','f_1xslhk','f_1qsjq','f_tcsl','f_dchk','f_lapw','f_tdx','f_qxpw','f_lshk','f_gqdslw','f_fyw','f_kdslw','f_1xspw','f_klcf','f_xxslw','f_njsl','f_wxlhk','f_fcw','f_jcslw','f_njxtpw','f_1sjhk','f_qklxw','f_yksl','f_fxslw','f_qmlxw','f_mxlcw','f_qthk','f_hzchhk','f_xcpw','f_yhy','f_hcsl','f_pplxwtj','f_jrgzlxw','f_xhlydg','f_azltw','f_1fylxw','f_1hxslw','f_nyhk','f_jphk','f_jysl','f_yalxw','f_brhk','f_cxlxw','f_ttsl','f_ytlxwtj','f_xqchk','f_yxzl','f_eair_zx','f_klfz','f_jzl','f_lhw','f_xscsw','f_djgj','f_tjslw','f_jclxw','f_ydlxw','f_xysltj','f_fxzl','f_1ttjq','f_jdlx','f_thtx','f_xyw','f_qlym','f_klextj','f_jylx','f_xtwdg','f_xhhktj','f_gtly','f_prpw','f_1hysd','f_jwlxw','f_zhslw','f_mmx','f_hktl','f_xjhk','f_bxj','f_hasl'";
        String temp[] = type.split(",");
        String resultParam = "";
        int i = 0;
        for (String s : temp) {
//            if (i < 4) {
            resultParam += (s.substring(1, s.length() - 1) + ",");
//            }
            ++i;
        }
        System.out.println(type);
        String mobile = "18611400087";
        String sdate = "2015-01-21";


        Map<String, String> paramMap = Maps.newHashMap();
        paramMap.put("mobile", "18611400087");
        paramMap.put("start", "2016-01-01");
        paramMap.put("type", resultParam);
        paramMap.put("uid", "sms_itf_01");
        paramMap.put("psw", "faef7c6eb7cff68467bee4eee66a3f11");
//        for (int j = 0; j < 10; ++j) {
        long start = System.currentTimeMillis();
        String data = HttpRequst1("http://l-smsmanage3.wap.cn1.qunar.com:9090/sms/sms!out.action", paramMap);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(data);
//        }
    }

    /**
     * add by panther
     *
     * @param url   url请求
     * @param param 请求参数
     * @return json数据
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.connect();
            // 获取所有响应头字段
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * add by panther
     *
     * @param url   请求url
     * @param param 参数
     * @return json数据
     */
    public static String request(String url, Map<String, String> param) {
        String requestParams = "";
        for (String s : param.keySet()) {
            requestParams += (s + "=" + param.get(s) + "&");
        }
        LOG.info(url + requestParams);
        return sendGet(url, requestParams);
    }


    public static String HttpRequst(String url, Map<String, String> param) {
        String ret = "ERROR_SERVER";
        CloseableHttpClient httpclient = null;
        HttpPost httpPost = null;
        long startTime = System.currentTimeMillis();
        try {
            HttpEntity reqEntity = null;
            if (param != null && param.size() > 0) {
                List<NameValuePair> formparams = new ArrayList<NameValuePair>(param.size());
                Set<Map.Entry<String, String>> entrySet = param.entrySet();
                for (Map.Entry<String, String> e : entrySet) {
                    String name = e.getKey();
                    String value = e.getValue();
                    NameValuePair pair = new BasicNameValuePair(name, value);
                    formparams.add(pair);
                }
                reqEntity = new UrlEncodedFormEntity(formparams, "utf-8");
            }
            httpPost = new HttpPost(url.indexOf("?") > 0 ? url.split("\\?")[0] : url);
            httpclient = httpBulder.build();
            if (reqEntity != null)
                httpPost.setEntity(reqEntity);
            httpPost.setConfig(requestConfig);
            CloseableHttpResponse response = null;
            try {
                response = httpclient.execute(httpPost);
                if (response == null) {
                    ret = "ERROR_RESPONSE";
                    return ret;
                }
                int statusCode = response.getStatusLine().getStatusCode();
                LOG.info("statusCode = " + statusCode);
                if (statusCode != 200) {
                    ret = "STATUS_NOT_200";
                    return ret;
                }
                HttpEntity resEntity = response.getEntity();
                ret = EntityUtils.toString(resEntity, "utf-8");
                //LOG.info("Response result:"+message);
            } catch (Exception e1) {
                LOG.error(e1.getMessage(), e1);
            } finally {
                if (response != null)
                    response.close();
            }
        } catch (Throwable e) {
            LOG.error("{}", e);
        } finally {
            if (httpPost != null)
                httpPost.releaseConnection();
            LOG.info("url=" + url + ",ret=" + ret + ",cost=" + (System.currentTimeMillis() - startTime) + " ms");
        }
        return ret;
    }


    public static String HttpRequst1(String url, Map<String, String> param) {
        String ret = "ERROR_SERVER";
        HttpPost httpPost = null;
        DefaultHttpClient httpClient = null;
        HttpResponse response = null;
        BufferedReader br = null;
        try {
            httpPost = new HttpPost(url);
            if (param != null && param.size() > 0) {
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                Iterator<String> iterator = param.keySet().iterator();
                while (iterator.hasNext()) {
                    String keyString = iterator.next();
                    NameValuePair nvp = new BasicNameValuePair(keyString, param.get(keyString));
                    list.add(nvp);
                }
                httpPost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
            }

            httpClient = new DefaultHttpClient();
            HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 1000000);
            HttpConnectionParams.setSoTimeout(httpClient.getParams(), 1000000);
            response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    br = new BufferedReader(new InputStreamReader(entity.getContent()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    ret = sb.toString().trim();
                }
            }

        } catch (Exception e) {
            LOG.error("Utils String HttpRequst(String url, Map<String, String> param) error e:", e);
        } finally {
            if (httpPost != null) {
                try {
                    httpPost.abort();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.getConnectionManager().shutdown();
                } catch (Exception e2) {
                    LOG.error(
                            "Utils String HttpRequst(String url, Map<String, String> param) finally error e2:",
                            e2);
                }
            }
        }
        return ret;

    }


}
