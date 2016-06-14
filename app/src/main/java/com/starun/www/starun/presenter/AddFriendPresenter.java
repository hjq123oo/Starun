package com.starun.www.starun.presenter;

/**
 * Created by yearsj on 2016/5/17.
 */
public interface AddFriendPresenter {

    /**
     * 获得查找的结果
     * @param keyword
     * @param user_id
     */
    public void getSearchResultList(String keyword, int user_id);

    /**
     * 发送添加好友请求
     * @param user_id1
     * @param user_id2
     */
    public void addFriend(String user_id1,String user_id2);
}
