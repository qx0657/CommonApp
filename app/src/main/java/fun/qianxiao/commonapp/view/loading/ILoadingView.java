package fun.qianxiao.commonapp.view.loading;

/**
 * Create by QianXiao
 * On 2020/6/23
 */
public interface ILoadingView {
    /**
     * Show Dialog
     *
     * @param msg Message
     */
    void openLoadingDialog(String msg);

    /**
     * Close Dialog
     */
    void closeLoadingDialog();
}
