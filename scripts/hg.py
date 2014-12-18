# -*- coding: utf-8 -*-

import numpy as np
import matplotlib.pyplot as plt
import mpl_toolkits.mplot3d.art3d as art3d

from mpl_toolkits.mplot3d import Axes3D
from matplotlib.patches import Arc, Wedge, Arrow, Circle, PathPatch, FancyArrowPatch, ConnectionStyle
from mpl_toolkits.mplot3d import proj3d
from astropy.wcs.docstrings import theta0

fig = plt.figure()
ax = fig.gca(projection='3d')
ax.set_aspect("equal")

# set axes scaling
ax.set_xlim3d(-4, 4)
ax.set_ylim3d(-4, 4)
ax.set_zlim3d(-4, 4)

# set 'camera' position
ax.azim = 14
ax.elev = 16
ax.dist = 5.5

plt.axis('off')

#draw sphere
def drawSphere(x=0,y=0,z=0,r=1):
    u, v = np.mgrid[0:2*np.pi:16j, 0:np.pi:11j]
    cx= x + np.cos(u)*np.sin(v) * r
    cy= y + np.sin(u)*np.sin(v) * r
    cz= z + np.cos(v) * r
    return ax.plot_wireframe(cx, cy, cz, color="grey", linestyle='dotted' )

drawSphere(r=2)

# Draw lat, long arrows
theta = 35
for i in ["y", "z"]:
    arc = Arc((0,0), 4, 4, angle=0, theta1=0, theta2=theta)
    ax.add_patch(arc)
    art3d.pathpatch_2d_to_3d(arc, z=0, zdir=i)
    #arrow = Arrow(0, 0, 0.4, 0.4, 0.4)
    #ax.add_patch(arrow)
    #ßart3d.pathpatch_2d_to_3d(arrow, z=2, zdir=i)

#earth
drawSphere(10,0,0,0.5)


class Arrow3D(FancyArrowPatch):
    def __init__(self, xs, ys, zs, *args, **kwargs):
        FancyArrowPatch.__init__(self, (0,0), (0,0), *args, **kwargs)
        self._verts3d = xs, ys, zs
    
    def draw(self, renderer):
        xs3d, ys3d, zs3d = self._verts3d
        xs, ys, zs = proj3d.proj_transform(xs3d, ys3d, zs3d, renderer.M)
        self.set_positions((xs[0],ys[0]),(xs[1],ys[1]))
        FancyArrowPatch.draw(self, renderer)

ax.plot([0,10],[0,0],[0,0], linewidth=1, color='grey', linestyle='dotted')

theta_rad = np.deg2rad(theta)
x = Arrow3D([np.cos(theta_rad)*2,np.cos(theta_rad+0.1)*2], [np.sin(theta_rad)*2,np.sin(theta_rad+0.1)*2], [0,0], mutation_scale=20, lw=1, arrowstyle="-|>", color="k", label='long')
y = Arrow3D([np.cos(theta_rad)*2,np.cos(theta_rad+0.1)*2], [0,0], [np.sin(theta_rad)*2,np.sin(theta_rad+0.1)*2], mutation_scale=20, lw=1, arrowstyle="-|>", color="k", label='lat')

ax.add_artist(x)
ax.add_artist(y)

# Labels
ax.text(2.2, 1.5, 0.1, 'latitude')
ax.text(2.2, -0.5, 1.5, 'longitude')
ax.text(10, -1, 0.5, 'Earth')
ax.text(0, -1.8, 1.8, 'Sun')

plt.show()