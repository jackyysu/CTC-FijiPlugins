package de.mpicbg.ulman.Mastodon.auxPlugins.TRAMarkers;

import org.scijava.command.Command;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

import net.imglib2.util.Util;
import net.imglib2.RealLocalizable;
import de.mpicbg.ulman.Mastodon.auxPlugins.TRAMarkersProvider;

@Plugin( type = SpheresWithFixedRadius.class, visible = false,
         name = "Specify the sphere radius in the image units (e.g. in microns):" )
public class SpheresWithFixedRadius implements TRAMarkersProvider.intersectionDecidable, Command
{
	@Parameter(min = "0", stepSize = "1")
	double fixedRadius = 0;

	//shortcut
	double fixedRadiusSq;

	@Override
	public void init()
	{
		fixedRadiusSq = fixedRadius*fixedRadius;
	}

	@Override
	public void setHalfBBoxInterval(final double[] halfBBoxSize, final double radius)
	{
		halfBBoxSize[0] = fixedRadius;
		halfBBoxSize[1] = fixedRadius;
		halfBBoxSize[2] = fixedRadius;
	}

	@Override
	public boolean isInside(final double[] distVec, final double radius)
	{
		final double lenSq = (distVec[0] * distVec[0]) + (distVec[1] * distVec[1]) + (distVec[2] * distVec[2]);
		return lenSq <= fixedRadiusSq;
	}

	@Override
	public String printInfo()
	{
		return "Sphere with fixed radius of "+fixedRadius;
	}

	@Override
	public void run() { /* intentionally empty */ }
}
