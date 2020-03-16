package zelosin.pack.Data;

import java.util.ArrayList;

public class CalculatingTask implements Runnable{

    private final static Integer THREAD_COUNT = 5;
    private static Float mThreadValueStep, mGlobalAccuracy;

    private static ArrayList<Thread> mCalculatingThreadList = new ArrayList<>();
    public static Float mGlobalResult;
    static boolean isReversed = false;

    private Float mCurrentUpEdge, mCurrentDownEdge;

    public static void initCalculationThreads(Float pGlobalUpEdge, Float pGlobalDownEdge, Float pGlobalAccuracy){
        isReversed = false;
        mGlobalResult = (float) 0;
        mGlobalAccuracy = pGlobalAccuracy;
        mCalculatingThreadList.clear();
        Float tCalculatedDownEdge = Math.min(pGlobalUpEdge, pGlobalDownEdge);
        Float tCalculatedUpEdge = Math.max(pGlobalUpEdge, pGlobalDownEdge);
        if(!tCalculatedUpEdge.equals(pGlobalUpEdge))
            isReversed = true;
        mThreadValueStep = (tCalculatedUpEdge - tCalculatedDownEdge)/ THREAD_COUNT;
        Float tCycleVar = tCalculatedDownEdge;
        for(int i = 0; i != THREAD_COUNT; i++) {
            mCalculatingThreadList.add(new Thread(new CalculatingTask(tCycleVar + mThreadValueStep, tCycleVar)));
            tCycleVar+= mThreadValueStep;
        }
    }

    public static void startAllThreads(){
        mCalculatingThreadList.forEach(Thread::start);
    }

    public static void join(GFGCell pResultingCell){
        mCalculatingThreadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        if(isReversed)
            pResultingCell.setResult(-mGlobalResult);
        else
            pResultingCell.setResult(mGlobalResult);
    }


    public CalculatingTask(Float mCurrentUpEdge, Float mCurrentDownEdge) {
        this.mCurrentUpEdge = mCurrentUpEdge;
        this.mCurrentDownEdge = mCurrentDownEdge;
    }


    @Override
    public void run() {
        Float tLocalResult = GFG.trapezoidal(mCurrentDownEdge, mCurrentUpEdge, mGlobalAccuracy);
        synchronized (mGlobalResult){
            mGlobalResult += tLocalResult;
        }
    }
}
