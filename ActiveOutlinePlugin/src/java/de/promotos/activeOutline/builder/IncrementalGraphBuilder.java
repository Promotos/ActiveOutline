package de.promotos.activeOutline.builder;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import de.promotos.activeOutline.builder.visitor.DelegatingVisitor;

public class IncrementalGraphBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = "ActiveOutlinePlugin.IncrementalGraphBuilder";
	private static final String TASK_NAME = "Build Active Outline Graph";

	@Override
	protected IProject[] build(final int kind, final Map<String, String> args, final IProgressMonitor monitor) throws CoreException {
		if (kind == FULL_BUILD) {
			fullBuild(monitor);
		} else {
			final IResourceDelta delta = getDelta(getProject());
			if (delta == null) {
				fullBuild(monitor);
			} else {
				incrementalBuild(delta, monitor);
			}
		}
		return null;
	}

	protected void fullBuild(final IProgressMonitor monitor) throws CoreException {
		try {
			monitor.beginTask(TASK_NAME, IProgressMonitor.UNKNOWN);
			getProject().accept(new DelegatingVisitor(monitor));
		} finally {
			monitor.done();
		}
	}

	protected void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor) throws CoreException {
		try {
			monitor.beginTask(TASK_NAME, IProgressMonitor.UNKNOWN);
			delta.accept(new DelegatingVisitor(monitor));
		} finally {
			monitor.done();
		}
	}
	
}
