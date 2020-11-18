package at.flockenberger.flocklib.flockutil;

public class PrintUtils
{

	public static void printArray(Object[] objects)
	{
		ObjectUtils.isNullThrow(objects, "Given array was null!");
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		int cnt = 1;
		for (Object obj : objects)
		{
			if (ObjectUtils.isNull(obj))
				continue;
			sb.append(obj.toString());
			cnt++;
			if(cnt != objects.length)
				sb.append(",");
			
		}
		sb.append("]");
		System.out.println(sb.toString());
	}

}
