package kumc.app.view;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import kumc.app.phi.MainActivity;

/**
 * Created by septboy on 2014. 11. 5..
 */
public class FragmentWrapper extends View {
    public FragmentWrapper(Context context) {
        super(context);
    }

    public FragmentWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FragmentWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    /*
    - withMeasureSpec 과 heightMeasureSpec 은 부모뷰로부터 결정된 치수가 넘어온다.
      MeasureSpec.getSize( widthMeasureSpec ) 을 통해 해당 치수를 얻어올 수 있다.
      MeasureSpec.getMode( widthMeasureSpec ) 을 통해 해당 치수의 mode 를 얻을 수 있다.
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /*
    - View 를 layout 시키는 역할을 한다. 즉 view 의 위치를 정해준다.
- onMeasure 를 통해 사이즈가 결정된 후에 onLayout 이 불린다.
- 부모뷰일때 주로 쓰이며, child 뷰를 붙일 때 위치를 정해주는데 사용한다.
- 넘어오는 파라메터는 어플리케이션 전체를 기준으로 위치가 넘어온다. ( 주의!! )

     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /*
    onDraw( Canvas canvas ) - 중요!
- View class 의 핵심이다.
- 실제로 화면을 그리는 영역으로 must be-implemented function 이다.
- 그리는 영역은 getMeasuredWidth() 와 getMeasruedHeight() 로 해당하는 rectangle 영역만 그려주면 된다.
     */

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public static FragmentWrapper getFragmentWrapper() {
        return null;
    }

    public FragmentWrapper addFragment(int i, Fragment fragment) {
        return null;
    }

    public FragmentWrapper addFragment(Fragment fm) {
        return null;
    }
}
